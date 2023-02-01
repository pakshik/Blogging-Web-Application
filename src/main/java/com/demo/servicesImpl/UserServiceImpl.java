package com.demo.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.User;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.payloads.UserDto;
import com.demo.repositories.UserRepo;
import com.demo.services.UserService;



@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto creatUser(UserDto userDto) {
           User user = this.dtoToUser(userDto);
           User savedUser = userRepo.save(user);
           return this.usertoUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId ));
				
		user.setAboutString(userDto.getAboutString());
		user.setEmailString(userDto.getEmailString());
	    user.setNameString(userDto.getNameString());
	    user.setPasswordString(userDto.getPasswordString());
	    
	    User updatedUser   =this.userRepo.save(user);
		return this.usertoUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId ));
		
		return this.usertoUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = userRepo.findAll(); 
		
	   List<UserDto> userDtos = users.stream().map(user->this.usertoUserDto(user)).collect(Collectors.toList());
		
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
	  
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId ));
		userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	private UserDto usertoUserDto(User user) {
		
	    UserDto userDto = this.modelMapper.map(user , UserDto.class);
	    return userDto;
		
	}

}
