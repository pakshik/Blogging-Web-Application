package com.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.payloads.ApiResponse;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String messageString = ex.getMessage();
		
		ApiResponse apiResponse = new ApiResponse(messageString,"false" );  
	
	    return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> argumentExceptionHandler(MethodArgumentNotValidException ex){
		Map<String, String> respMap = new HashMap<>();
 		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldNameString = ((FieldError) error).getField();
			String messageString = 	error.getDefaultMessage();
			respMap.put(fieldNameString, messageString);
		});
		
		
	    return new ResponseEntity<Map<String,String>>( respMap , HttpStatus.BAD_REQUEST);
	}
	
}
