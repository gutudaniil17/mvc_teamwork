package com.example.mvc_teamwork.repository;

import com.example.mvc_teamwork.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAll();
    Optional<Product> findByName(String name);

    Product save(Product product);

    void deleteById(int id);

    @Transactional
    @Modifying
    @Query("update Product p set p.name = :name where p.id = :id")
    void updateProductNameById(String name, int id);

    @Transactional
    @Modifying
    @Query("update Product p set p.price = :price where p.id = :id")
    void updateProductPriceById(int price, int id);
}
