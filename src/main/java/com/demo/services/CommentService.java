package com.demo.services;

import com.demo.payloads.CommentDto;

public interface CommentService {
	public CommentDto createComment(CommentDto commentDto , Integer postId);
	 
	public void  deleteComment(Integer commentId);
	
	
}
