package com.example.mvc_teamwork.service.category;

import com.example.mvc_teamwork.entity.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    void deleteCategory(int id);

    void changeCategory(String name, int id);

    Category findByName(String name);
    Category findById(int id);

    List<Category> findAll();
}
