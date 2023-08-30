package com.example.mvc_teamwork.order;

import com.example.mvc_teamwork.entity.Order;
import com.example.mvc_teamwork.entity.ShoppingCart;
import com.example.mvc_teamwork.repository.OrderRepository;
import com.example.mvc_teamwork.repository.ShoppingCartRepository;
import com.example.mvc_teamwork.security.entity.User;
import com.example.mvc_teamwork.security.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Test
    @DirtiesContext
    public void testSaveOrderWithUserAndShoppingCart() {
        User user = new User();
        userRepository.save(user);

        ShoppingCart cartItem = new ShoppingCart();
        shoppingCartRepository.save(cartItem);

        Order order = new Order();
        order.setUser(user);
        List<ShoppingCart> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        order.setCartItems(cartItems);

        orderRepository.save(order);

        List<Order> orders = orderRepository.findAll();
        Assertions.assertThat(orders).hasSize(1);
    }

    @Test
    @DirtiesContext
    public void testFindById() {
        Order order = new Order();
        orderRepository.save(order);

        Optional<Order> savedOrder = orderRepository.findById(order.getId());
        Assertions.assertThat(savedOrder).isPresent();
    }

    @Test
    @DirtiesContext
    public void testDeleteOrder() {
        Order order = new Order();
        orderRepository.save(order);

        orderRepository.deleteById(order.getId());

        Optional<Order> deletedOrder = orderRepository.findById(order.getId());
        Assertions.assertThat(deletedOrder).isEmpty();
    }
}
