package com.example.mvc_teamwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "promos")
@Getter
@Setter
@NoArgsConstructor
public class Promo {
    @Id
    @GeneratedValue
    private int id;
    private String code;
}
