package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entity.Category;
import com.demo.entity.Post;
import com.demo.entity.User;


public interface PostRepo extends JpaRepository<Post, Integer>  {

	List<Post> findByUserRefID(User userRefID);
    List<Post> findByCategoryRefId(Category categoryRefId);
    
  
    @Query("SELECT p FROM Post p WHERE p.postTitle LIKE :title")
    List<Post> searchByTitleLike(@Param("title") String title);
}
