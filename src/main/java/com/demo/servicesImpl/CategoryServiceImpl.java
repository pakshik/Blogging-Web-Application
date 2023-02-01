package com.demo.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Category;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.payloads.CategoryDto;
import com.demo.repositories.CategoryRepo;
import com.demo.services.*;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		  Category category = this.dtoToCategory(categoryDto);
          Category savedCategory = categoryRepo.save(category);
          return this.categorytoCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId ));
		
		category.setCategoryDescriptionString(categoryDto.getCategoryDescriptionString());
		category.setCategoryTitleString(categoryDto.getCategoryTitleString());
	    
	    Category updatedCategory   =this.categoryRepo.save(category);
		return this.categorytoCategoryDto(updatedCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId ));
		
		return this.categorytoCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> category = categoryRepo.findAll(); 
		
	    List<CategoryDto> categoryDtos = category.stream().map(c->this.categorytoCategoryDto(c)).collect(Collectors.toList());
			
			
		return categoryDtos;

	}

	@Override
	public void deleteCategory(Integer categoryId) {
        
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId ));
		categoryRepo.delete(category);                                                                                          
		
	}
    
	private Category dtoToCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}
	private CategoryDto categorytoCategoryDto(Category category) {
		
	    CategoryDto categoryDto = this.modelMapper.map(category , CategoryDto.class);
	    return categoryDto;
		
	}
	
}
