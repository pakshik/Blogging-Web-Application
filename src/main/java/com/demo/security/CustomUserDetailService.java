package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.entity.User;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.repositories.UserRepo;


@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired	
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
		User user =		this.userRepo.findByEmailString(username).orElseThrow(()-> new ResourceNotFoundException("User", "Email"+username, 0));
		return (UserDetails) user;
	}



}
