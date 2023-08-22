package com.example.mvc_teamwork.entity;

import com.example.mvc_teamwork.repository.CategoryRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private int price;
    private int categoryId;
    private int availableQuantity;
}
