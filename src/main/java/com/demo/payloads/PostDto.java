package com.demo.payloads;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postId; 
	
	private String postTitle;
	
	private String postContent;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto categoryRefId;
	
	private UserDto userRefID;
	
	private Set<CommentDto> comments = new HashSet<>();
}
