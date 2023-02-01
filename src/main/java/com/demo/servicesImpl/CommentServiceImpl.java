package com.demo.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Comment;
import com.demo.entity.Post;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.payloads.CommentDto;
import com.demo.repositories.CommentRepo;
import com.demo.repositories.PostRepo;
import com.demo.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentRepo commentRepo;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId)); 
		Comment comment = modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
        Comment updatedComment =  	this.commentRepo.save(comment);
		
		return this.modelMapper.map(updatedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "CommentId", commentId)); 
		
		this.commentRepo.delete(comment);
	}

}
