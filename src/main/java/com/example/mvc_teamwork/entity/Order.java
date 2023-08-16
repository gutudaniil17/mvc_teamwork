package com.example.mvc_teamwork.entity;

import com.example.mvc_teamwork.security.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String orderDescription;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = ShoppingCart.class)
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private List<ShoppingCart> cartItems;

}
