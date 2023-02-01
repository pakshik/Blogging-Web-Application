package com.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Users")
@NoArgsConstructor
public class User implements UserDetails {

	
	 @Id
	 @GeneratedValue (strategy = GenerationType.AUTO)
	 private int id ; 
	 
	 
	 private String nameString ;
	 
	 @Column(name = "userName" , nullable = false , length = 100)
	 private String emailString ;
	 
	 @Column(name = "password" , nullable = false , length =  100 )
	 private String passwordString;
	 
	 
	 private String aboutString;
	
	 @OneToMany(mappedBy = "userRefID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<Post> posts = new ArrayList<>();
	 
	 
	 @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	 @JoinTable(name =  "user_role",joinColumns = @JoinColumn(referencedColumnName =  "id" , name =  "user") , inverseJoinColumns = @JoinColumn(referencedColumnName =  "id" ,name = "role"))
	 private Set<Role> roles = new HashSet<>();


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
      List<SimpleGrantedAuthority> authorities =   this.roles.stream().map((r)-> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList()); 
		return authorities;
	}


	@Override
	public String getPassword() {
		return this.getPasswordString();
	}


	@Override
	public String getUsername() {
		return this.emailString;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}
	 
}
