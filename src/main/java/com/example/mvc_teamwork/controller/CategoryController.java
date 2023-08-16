package com.example.mvc_teamwork.controller;

import com.example.mvc_teamwork.entity.Category;
import com.example.mvc_teamwork.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/find_by_name_category/{category}")
    public String findCategoryByName(@PathVariable String category) {
        return categoryService.findByName(category) == null ? "No such category" : toString();
    }

    @GetMapping("/find_all_categories")
    public String findAllCategories() {
        return categoryService.findAll().toString();
    }

    @GetMapping("/find_all_products/{category}")
    public String findAllProducts(@PathVariable String category) {
        return categoryService.findByName(category)
                .getProducts().toString();
    }


    @PostMapping("/admin/update_category/{id}")
    public String updateCategory(
            @PathVariable int id,
            @RequestBody String name
    ) {
        categoryService.changeCategory(name, id);
        return "ok";
    }

    @PostMapping("/admin/add_category")
    public String addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return "done";
    }

    @DeleteMapping("/admin/delete_category/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "lol";
    }
}
