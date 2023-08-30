package com.example.mvc_teamwork.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartDTO {
    private String productName;
    private int quantity;
    private float amount;
}
