package com.demo.services;

import com.demo.payloads.CategoryDto;

public interface CategoryService {

	
	   CategoryDto createCategory(CategoryDto categoryDto); 
	   
	   CategoryDto updateCategory(CategoryDto categoryDto , Integer categoryId);
	   
	   CategoryDto getCategoryById(Integer categoryId);
	   
	   java.util.List<CategoryDto> getAllCategory();

	   void deleteCategory(Integer categoryId) ;
}
