package com.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category")
public class Category {

	
	@Id
	@GeneratedValue(strategy = 	GenerationType.IDENTITY)
	private Integer categoryIdInteger;
	
	@Column(name = "title" , length =  100 , nullable = false )
	private String categoryTitleString;
	
	@Column(name = "description" )
	private String categoryDescriptionString;

    
	@OneToMany(mappedBy = "categoryRefId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();
	 
}