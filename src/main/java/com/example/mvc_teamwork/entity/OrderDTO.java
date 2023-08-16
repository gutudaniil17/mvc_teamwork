package com.example.mvc_teamwork.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private String orderDescription;
    private List<ShoppingCart> cartItems;
    private String userEmail;
    private String userFirstName;
    private String usersLastName;

}
