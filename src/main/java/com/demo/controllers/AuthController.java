package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.payloads.JwtAuthRequest;
import com.demo.payloads.JwtAuthResponse;
import com.demo.security.JwtTokenHelper;

@RestController
@RequestMapping("/api")
public class AuthController {

	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request){
		 
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = 	this.userDetailsService.loadUserByUsername(request.getUsername());
	    String tokenString = this.jwtTokenHelper.generateToken(userDetails);  
	    JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
	    jwtAuthResponse.setTokenString(tokenString);
	    return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
	}
	
	private void authenticate (String username , String password ) {
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);	
		}catch (BadCredentialsException e) {
			System.out.println("Bad Credential!!");
		}
		
		
		
	}
	
	
}
