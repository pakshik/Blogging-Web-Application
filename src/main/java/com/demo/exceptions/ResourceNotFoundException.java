package com.demo.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	
	String resourceNameString;
	String filedNameString; 
    Integer   filedValue;
	public ResourceNotFoundException(String resourceNameString, String filedNameString, Integer filedValue) {
		super(String.format("%s not found with %s : %s", resourceNameString ,filedNameString , filedValue));
		this.resourceNameString = resourceNameString;
		this.filedNameString = filedNameString;
		this.filedValue =  filedValue;
	}
	
}
