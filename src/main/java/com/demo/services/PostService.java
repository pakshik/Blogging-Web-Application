package com.demo.services;

import java.util.List;

import com.demo.payloads.PostDto;
import com.demo.payloads.PostResponse;

public interface PostService {

	//create post 
	  
	public PostDto createPost(PostDto postDto ,  Integer userId , Integer categoryId) ;
	
	// update post 
	
	public PostDto updatePost(PostDto postDto , Integer postId);
	
	// delete post 
	public void deletePost(Integer postId);	 
	
	// get all post 
	public PostResponse getAllPost(Integer pageNumber ,Integer pageSize ,String sortBy ,String sortDir );
 	
	// get single post 
	public PostDto getOnePostByID(Integer postId);
	
	// get all post by userId 
	public List<PostDto> getAllPostByUserId(Integer userId);
	
	// get all post by categoryId
	public List<PostDto> getAllPostByCategoryid(Integer categoryId);

	
	// search post 
	public List<PostDto> searchPost(String title);
	
	
	
	
	
	
}
