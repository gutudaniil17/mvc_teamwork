package com.example.mvc_teamwork.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.mvc_teamwork.entity.Category;
import com.example.mvc_teamwork.repository.CategoryRepository;
import com.example.mvc_teamwork.service.category.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCategory() {
        Category category = new Category();
        category.setName("Test Category");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        categoryService.addCategory(category);
        verify(categoryRepository).save(category);
    }

    @Test
    void testFindByName() {
        String categoryName = "Test Category";
        Category mockCategory = new Category();
        mockCategory.setId(1);
        mockCategory.setName(categoryName);

        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(mockCategory));

        Category foundCategory = categoryService.findByName(categoryName);
        assertThat(foundCategory).isEqualTo(mockCategory);
    }

    @Test
    void testFindById() {
        int categoryId = 1;
        Category mockCategory = new Category();
        mockCategory.setId(categoryId);
        mockCategory.setName("Test Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));

        Category foundCategory = categoryService.findById(categoryId);
        assertThat(foundCategory).isEqualTo(mockCategory);
    }

    @Test
    void testDeleteCategory() {
        int categoryId = 1;

        categoryService.deleteCategory(categoryId);

        verify(categoryRepository).deleteById(categoryId);
    }

    @Test
    void testChangeCategory() {
        int categoryId = 1;
        String newName = "Updated Category Name";

        categoryService.changeCategory(newName, categoryId);

        verify(categoryRepository).updateCategoryNameById(newName, categoryId);
    }

    @Test
    void testFindAll() {
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(new Category());

        when(categoryRepository.findAll()).thenReturn(mockCategories);

        List<Category> categories = categoryService.findAll();
        assertThat(categories).hasSize(mockCategories.size());
    }

}

