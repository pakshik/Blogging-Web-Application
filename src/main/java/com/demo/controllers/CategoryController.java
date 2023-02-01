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
import com.demo.payloads.CategoryDto;
import com.demo.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
        
	   @Autowired
       private CategoryService categoryService;
	
	// Post Mapping for creating user 
		@PostMapping("/")
		public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
			
			CategoryDto createcaCategoryDto  = categoryService.createCategory(categoryDto); 
			return new ResponseEntity<CategoryDto>(createcaCategoryDto, HttpStatus.CREATED);
		}
		
		//put -update user 
		@PutMapping("/{categoryId}")
		public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable("categoryId") Integer categoryid ){
			CategoryDto updatedCategoryDto = categoryService.updateCategory(categoryDto, categoryid);
			return  ResponseEntity.ok(updatedCategoryDto);
		}
		
		
		// Delete a user 
		@DeleteMapping("/{categoryId}")
		public ResponseEntity<ApiResponse> deleteCategory(@Valid @PathVariable("categoryId") Integer categoryId){
		    categoryService.deleteCategory(categoryId);
		    return new ResponseEntity(new ApiResponse("message" , "Successfully Deleted!! ") , HttpStatus.OK);
		}
		
		
		
	    /// Get a one user 
		@GetMapping("/{categoryId}")
		public ResponseEntity<CategoryDto> getOneUser(@Valid @PathVariable("categoryId") Integer categoryidInteger ){
			
			
			CategoryDto categoryDto = categoryService.getCategoryById(categoryidInteger);
			
			return ResponseEntity.ok(categoryDto);
		}
		
		
		/// Get all user 
		@GetMapping("/")
		public ResponseEntity<List<CategoryDto>> getAllUserEntity(){
			List<CategoryDto> list = categoryService.getAllCategory();
			return  ResponseEntity.ok(list);
		}
		
	
	
	
}
