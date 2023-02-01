package com.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.payloads.ApiResponse;
import com.demo.payloads.PostDto;
import com.demo.payloads.PostResponse;
import com.demo.services.FileService;
import com.demo.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path ; 
	/// create post 
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			){
		
		
		System.out.println("postDto " + postDto);
		PostDto	newPostDto = 	postService.createPost(postDto, userId, categoryId); 
		
		return new ResponseEntity<>(newPostDto,  HttpStatus.CREATED);
 	}
	
	/// get post by user id 
	@RequestMapping("/user/{userid}/posts")
	public ResponseEntity<List<PostDto>> getPostByUserId(
			@PathVariable Integer userid) {
	
	     List<PostDto> posts =  postService.getAllPostByUserId(userid);
		
	     return new ResponseEntity<List<PostDto>>(posts ,HttpStatus.OK);
	}
	
	// get post by category  id 
	@RequestMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategoryId(
			@PathVariable Integer  categoryId) {
	
	     List<PostDto> posts =  postService.getAllPostByCategoryid(categoryId);
		
	     return new ResponseEntity<List<PostDto>>(posts ,HttpStatus.OK);
	}
	
	// get all post 
	@RequestMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber" ,defaultValue =  "0" , required =  false ) Integer pageNumber,
			@RequestParam(value = "pageSize" ,defaultValue =  "10" , required =  false ) Integer pageSize,
			@RequestParam(value = "sortBy" ,defaultValue =  "postId" , required =  false ) String sortBy,
			@RequestParam(value = "sortDir" ,defaultValue =  "asc" , required =  false ) String sortDir
			){
		PostResponse postResponse = postService.getAllPost(pageNumber , pageSize , sortBy , sortDir);
		return new ResponseEntity<PostResponse>(postResponse , HttpStatus.OK );
	}
	
	// get  post by id 
	@RequestMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(
			@PathVariable Integer postId){
		PostDto postDtos = postService.getOnePostByID(postId);
		return new ResponseEntity<PostDto>(postDtos , HttpStatus.OK );
	}
	
	// update post 
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(
			@RequestBody PostDto postDto,
			@PathVariable Integer postId){
         PostDto updatePostDto =     postService.updatePost(postDto, postId) ; 
         return new ResponseEntity<PostDto>(updatePostDto, HttpStatus.OK);
	}
	
	
	// delete post 
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId){
          postService.deletePost(postId) ; 
          return new ApiResponse("Post Deleted Successfully!!" , "true")   ;
	}
	
	@RequestMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> findPostByTitle(@PathVariable("keywords") String keywords){
		List<PostDto> listPostDtos = postService.searchPost(keywords);
		return new ResponseEntity<>(listPostDtos , HttpStatus.OK);
	}
	
	
	@PostMapping("/post/image/upload/{postId}")
	public  ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		String fileName = this.fileService.uploadImage(path, image);
		PostDto postDto = this.postService.getOnePostByID(postId);
		postDto.setImageName(fileName);
		PostDto updatedPostDto = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPostDto , HttpStatus.OK);
	}

	
	@GetMapping(value = "/post/image/{imageName}" , produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE )
	public void downloadImage(
			@PathVariable("imageName") String imageName , 
			HttpServletResponse httpServletResponse) throws IOException {
		
		InputStream resourceInputStream = this.fileService.getResource(path, imageName);
		httpServletResponse.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourceInputStream, httpServletResponse.getOutputStream());
		
	}
	
	
}
