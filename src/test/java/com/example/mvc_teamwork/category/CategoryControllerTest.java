package com.example.mvc_teamwork.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.example.mvc_teamwork.controller.CategoryController;
import com.example.mvc_teamwork.entity.Category;
import com.example.mvc_teamwork.service.category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCategoryByName() {
        String categoryName = "Test Category";

        when(categoryService.findByName(categoryName)).thenReturn(null);

        String result = categoryController.findCategoryByName(categoryName);
        assertThat(result).isEqualTo("No such category");
    }

    @Test
    void testFindAllCategories() {
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(new Category());

        when(categoryService.findAll()).thenReturn(mockCategories);

        String result = categoryController.findAllCategories();
        assertThat(result).isEqualTo(mockCategories.toString());
    }

    @Test
    void testUpdateCategory() {
        int categoryId = 1;
        String newName = "Updated Category Name";

        String result = categoryController.updateCategory(categoryId, newName);
        assertThat(result).isEqualTo("ok");

        verify(categoryService).changeCategory(newName, categoryId);
    }

    @Test
    void testAddCategory() {
        Category category = new Category();
        category.setName("Test Category");

        String result = categoryController.addCategory(category);
        assertThat(result).isEqualTo("done");

        verify(categoryService).addCategory(category);
    }

    @Test
    void testDeleteCategory() {
        int categoryId = 1;

        String result = categoryController.deleteCategory(categoryId);
        assertThat(result).isEqualTo("lol");

        verify(categoryService).deleteCategory(categoryId);
    }

}
