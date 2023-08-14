package com.example.mvc_teamwork.service.category;

import com.example.mvc_teamwork.entity.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);
    Category findByName(String name);
    void deleteCategory(int id);
    List<Category> findAll();
}
