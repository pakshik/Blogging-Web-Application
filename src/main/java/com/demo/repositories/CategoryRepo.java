package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Category;

public interface CategoryRepo  extends JpaRepository<Category, Integer>{

}
