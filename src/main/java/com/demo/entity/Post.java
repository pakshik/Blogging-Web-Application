package com.demo.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Post {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "postId")
	private Integer postId; 
	
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postTitle=" + postTitle + ", postContent=" + postContent + ", imageName="
				+ imageName + ", addedDate=" + addedDate + ", categoryRefId=" + categoryRefId + ", userRefID="
				+ userRefID + ", comments=" + comments + "]";
	}

	@Column(name = "postTitle"  , length =  100 , nullable = false)
	private String postTitle;
	
	@Column(name= "postContent" , length = 100 , nullable =  false)
	private String postContent;
	
	
	@Column(name = "imageName" )
	private String imageName;
	
	
	@Column(name = "addedDate")
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "categoryRefId")
	private Category categoryRefId;
	
	
	@ManyToOne
	@JoinColumn(name =  "userRefID")
	private User userRefID;
	
	@OneToMany(mappedBy = "post" , cascade = CascadeType.ALL  ,fetch = FetchType.EAGER)
	private Set<Comment> comments=new HashSet<>();

}
