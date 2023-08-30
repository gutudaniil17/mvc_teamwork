package com.example.mvc_teamwork.order;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.mvc_teamwork.entity.Order;
import com.example.mvc_teamwork.entity.Product;
import com.example.mvc_teamwork.entity.ShoppingCart;
import com.example.mvc_teamwork.repository.OrderRepository;
import com.example.mvc_teamwork.repository.ProductRepository;
import com.example.mvc_teamwork.service.order.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderDetail() {
        int orderId = 1;
        Order mockOrder = new Order();
        mockOrder.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        Order foundOrder = orderService.getOrderDetail(orderId);
        assertThat(foundOrder).isEqualTo(mockOrder);
    }

    @Test
    void testGetCartAmount() {
        ShoppingCart cartItem = new ShoppingCart();
        cartItem.setProductId(1);
        cartItem.setQuantity(3);

        Product product = new Product();
        product.setId(1);
        product.setPrice(10);
        product.setAvailableQuantity(5);

        when(productRepository.findById(cartItem.getProductId())).thenReturn(Optional.of(product));

        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        shoppingCartList.add(cartItem);

        float cartAmount = orderService.getCartAmount(shoppingCartList);
        assertThat(cartAmount).isEqualTo(30);
    }

    @Test
    void testSaveOrder() {
        Order order = new Order();

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.saveOrder(order);
        assertThat(savedOrder).isEqualTo(order);
    }
}
