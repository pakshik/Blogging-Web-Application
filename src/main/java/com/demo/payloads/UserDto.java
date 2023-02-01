package com.demo.payloads;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	 private int id ; 
	 
	 @NotEmpty
	 @Size(min = 4 , message =  "Username must be a minimum of 4 charactor")
	 private String nameString ;
	 
	 @Email(message =  "Email Address Is not Valid!!")
	 private String emailString ;
	 
	 @NotEmpty
	 @Size (min = 4 , max = 10 , message =  "password must be a minimum of 4 charactor and maximum 10 charactor")
	 private String passwordString;
	 
	 @NotEmpty
	 private String aboutString;
	
}
