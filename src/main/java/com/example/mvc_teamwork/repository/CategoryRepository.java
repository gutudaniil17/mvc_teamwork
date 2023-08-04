package com.example.mvc_teamwork.repository;

import com.example.mvc_teamwork.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO test it
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
