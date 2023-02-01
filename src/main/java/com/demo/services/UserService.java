package com.demo.services;

import com.demo.payloads.UserDto;


public interface  UserService {

   UserDto creatUser(UserDto userDto); 
   
   UserDto updateUser(UserDto userDto , Integer userId);
   
   UserDto getUserById(Integer userId);
   
   java.util.List<UserDto> getAllUsers();

   void deleteUser(Integer userId) ;
   
   

}
