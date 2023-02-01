package com.demo.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

	 private java.util.List<PostDto> content;
	 private int pageNumber ; 
	 private int pageSize ;
	 private int totalElements ; 
	 private int totalPages;
	 private boolean lastPage;
	
	
	
}
