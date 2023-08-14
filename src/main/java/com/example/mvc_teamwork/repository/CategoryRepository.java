package com.example.mvc_teamwork.repository;

import com.example.mvc_teamwork.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category save(Category category);

    Category findByName(String name);

    void deleteById(int id);

    List<Category> findAll();

    @Modifying
    @Query("update Category c set c.name = :name where c.id = :id")
    Category updateCategoryNameById(String name, int id);

    Category findById(int id);
}
