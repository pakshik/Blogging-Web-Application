package com.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.payloads.ApiResponse;
import com.demo.payloads.UserDto;
import com.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
    private  UserService userService;
	
	
	
	// Post Mapping for creating user 
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createUserDto  = userService.creatUser(userDto); 
		return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
	}
	
	//put -update user 
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto , @PathVariable("userId") Integer userid ){
		
		UserDto updatedUser = userService.updateUser(userDto, userid);
		return  ResponseEntity.ok(updatedUser);
	}
	
	
	// Delete a user 
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@Valid @PathVariable("userId") Integer userId){
	    userService.deleteUser(userId);
	    return new ResponseEntity(new ApiResponse("message" , "Successfully Deleted!! ") , HttpStatus.OK);
	}
	
	
	
    /// Get a one user 
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getOneUser(@Valid @PathVariable("userId") Integer useridInteger ){
		
		UserDto userDto = userService.getUserById(useridInteger);
		
		return ResponseEntity.ok(userDto);
	}
	
	
	/// Get all user 
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUserEntity(){
		List<UserDto> list = userService.getAllUsers();
		return  ResponseEntity.ok(list);
	}
	
}
