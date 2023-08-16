package com.example.mvc_teamwork.service.order;

import com.example.mvc_teamwork.entity.Order;
import com.example.mvc_teamwork.entity.ShoppingCart;

import java.util.List;

public interface OrderService {
    public Order getOrderDetail(int orderId);
    public float getCartAmount(List<ShoppingCart> shoppingCartList);
    public Order saveOrder(Order order);
}
