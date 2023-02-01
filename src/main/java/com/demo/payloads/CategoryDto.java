package com.demo.payloads;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {


	private Integer categoryIdInteger;
	
	@NotEmpty
	@Size(min = 4 , message = "Title should be greate than 4 length ")
	private String categoryTitleString;
	
	@NotEmpty
	@Size(min = 10 , message = "Description lenght should be greater than 10 ")
	private String categoryDescriptionString;
	
}
