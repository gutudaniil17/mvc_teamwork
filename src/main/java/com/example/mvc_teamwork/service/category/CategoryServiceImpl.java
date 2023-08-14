package com.example.mvc_teamwork.service.category;

import com.example.mvc_teamwork.entity.Category;
import com.example.mvc_teamwork.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findByName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        return category.isPresent()?category.get():null;
    }

    @Override
    public Category findById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.isPresent()?category.get():null;
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category changeCategory(String name, int id) {
        return categoryRepository.updateCategoryNameById(name,id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
