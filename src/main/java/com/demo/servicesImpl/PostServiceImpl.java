package com.demo.servicesImpl;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.entity.Category;
import com.demo.entity.Comment;
import com.demo.entity.Post;
import com.demo.entity.User;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.payloads.CategoryDto;
import com.demo.payloads.CommentDto;
import com.demo.payloads.PostDto;
import com.demo.payloads.PostResponse;
import com.demo.payloads.UserDto;
import com.demo.repositories.CategoryRepo;
import com.demo.repositories.PostRepo;
import com.demo.repositories.UserRepo;
import com.demo.services.PostService;


@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto , Integer userId , Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		 
		Post post = new Post(); 
	
		post.setCategoryRefId(category);
		post.setUserRefID(user);
		post.setImageName("default.png");
		post.setPostContent(postDto.getPostContent());
		post.setPostTitle(postDto.getPostTitle());
		post.setAddedDate(new Date(0));
	    Post savedPost = postRepo.save(post);

	    return modelMapper.map(savedPost , PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		
		post.setPostContent(postDto.getPostContent());
		post.setPostTitle(postDto.getPostTitle());
		post.setImageName(postDto.getImageName());
	    post.setAddedDate(postDto.getAddedDate());
	    
		
		Post updatePost = postRepo.save(post);
		
		return modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		
		this.postRepo.delete(post);
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber ,Integer pageSize ,String sortBy ,String sortDir ) {
		
	   Sort sort =  sortDir.equalsIgnoreCase("asc") ?  Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
	   
	   org.springframework.data.domain.Pageable pageable =  PageRequest.of(pageNumber, pageSize, sort);
 	  
	   Page<Post> pagePost = this.postRepo.findAll(pageable);
	   	
	   List<Post> list = pagePost.getContent();
		
	   List<PostDto> listDtos = list.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
	   PostResponse postResponse = new PostResponse();
	   postResponse.setContent(listDtos);
	   postResponse.setPageNumber(pagePost.getNumber());
       postResponse.setPageSize(pagePost.getSize()); 
       postResponse.setTotalElements(pagePost.getNumberOfElements());
       postResponse.setTotalPages(pagePost.getTotalPages());
       postResponse.setLastPage(pagePost.isLast());
       
       return postResponse;
	}

	@Override
	public PostDto getOnePostByID(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
        return postToPostDto(post);
	}

	@Override
	public List<PostDto> getAllPostByUserId(Integer userId) {
		
	   User user =  userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Userid", userId));
	   List<Post> list  =  this.postRepo.findByUserRefID(user);
	   
	   List<PostDto> listDtos = list.stream().map((li)->modelMapper.map(li, PostDto.class)).collect(Collectors.toList());
	   
	   return listDtos;
	}

	@Override
	public List<PostDto> getAllPostByCategoryid(Integer categoryId) {
	   Category category =  categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Categoryid", categoryId));
	   List<Post> list  =  this.postRepo.findByCategoryRefId(category);
	   
	   List<PostDto> listDtos = list.stream().map((li)->modelMapper.map(li, PostDto.class)).collect(Collectors.toList());
	   
	   return listDtos;
		 
	}
	
	
	

	@Override
	public List<PostDto> searchPost(String title) {
		List<Post> list = this.postRepo.searchByTitleLike("%"+ title +"%" );
		
		List<PostDto> listDtos = list.stream().map((li)-> modelMapper.map(li, PostDto.class)).collect(Collectors.toList());
		
		return listDtos;
	}
	
	
	public PostDto postToPostDto(Post post) {
		PostDto postDto = new PostDto();
//		postDto.setAddedDate(post.getAddedDate());
		postDto.setCategoryRefId(modelMapper.map(post.getCategoryRefId(), CategoryDto.class));
		postDto.setUserRefID(modelMapper.map( post.getUserRefID(),UserDto.class));
		postDto.setPostContent(post.getPostContent());
		postDto.setPostId(post.getPostId());
		postDto.setPostTitle(post.getPostTitle());
		postDto.setImageName(post.getImageName());
		
		Set<Comment> list = post.getComments();
		Set<CommentDto> listDtos = list.stream().map((li)->commentToCommentDto(li)).collect(Collectors.toSet());
 		postDto.setComments(listDtos);
 		
 		return postDto;
	}
	
	
	public com.demo.payloads.CommentDto commentToCommentDto(Comment Comment) {
		
		CommentDto commentDto = new CommentDto();
		
		commentDto.setContent(Comment.getContent());
		commentDto.setId(commentDto.getId());
		
		return commentDto;
	}


}
