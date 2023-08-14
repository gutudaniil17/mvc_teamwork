package com.example.mvc_teamwork.repository;

import com.example.mvc_teamwork.entity.Category;
import com.example.mvc_teamwork.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category save(Category category);
    Category findByName(String name);
    void deleteById(int id);
    List<Category> findAll();
}
