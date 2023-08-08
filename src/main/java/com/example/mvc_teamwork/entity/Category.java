package com.example.mvc_teamwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categoties")
@Setter
@Getter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(mappedBy = "categoryId",cascade = CascadeType.ALL)
    private List<Product> products;
}
