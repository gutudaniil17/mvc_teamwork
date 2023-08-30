package com.example.mvc_teamwork.shoppingCart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.example.mvc_teamwork.controller.ShoppingCartController;
import com.example.mvc_teamwork.entity.*;
import com.example.mvc_teamwork.security.config.JwtService;
import com.example.mvc_teamwork.security.entity.User;
import com.example.mvc_teamwork.service.order.OrderService;
import com.example.mvc_teamwork.service.product.ProductService;
import com.example.mvc_teamwork.service.user.UserService;
import com.example.mvc_teamwork.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
/*Annotations like @Mock, @InjectMocks, and @BeforeEach are used for setting up the test environment and mocking.
* Dependencies are mocked using @Mock annotations.
The @InjectMocks annotation injects the mocked dependencies into the controller.
The @BeforeEach method initializes the mocks before each test.*/
class ShoppingCartControllerTest {

    @Mock
    private OrderService orderService;
    @Mock
    private ProductService productService;
    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        ProductDTO productDTO = new ProductDTO();
        when(productService.getAllProducts()).thenReturn(Collections.singletonList(productDTO));

        ResponseEntity<List<ProductDTO>> response = shoppingCartController.getAllProducts();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(productDTO);
        /*The productService.getAllProducts method is mocked to return a list containing a single ProductDTO.
The response is checked to ensure the status code is HttpStatus.OK and the response body contains the expected product.*/
    }

    @Test
    void testGetOrderDetails() {
        int orderId = 1;
        Order mockOrder = new Order();
        mockOrder.setId(orderId);
        when(orderService.getOrderDetail(orderId)).thenReturn(mockOrder);

        ResponseEntity<Order> response = shoppingCartController.getOrderDetails(orderId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(mockOrder);
        /*The orderService.getOrderDetail method is mocked to return a mock Order object.
The response is checked to ensure the status code is HttpStatus.OK and the response body matches the expected Order object.*/
    }

    @Test
    void testPlaceOrder() {
        // Mocking various components and services
        // Mocking orderDTO and cart item
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderDescription("Test Order Description");
        ShoppingCart cartItem = new ShoppingCart();
        orderDTO.setCartItems(Collections.singletonList(cartItem));

        // Mocking cart amount calculation
        float amount = 50.0f;
        when(orderService.getCartAmount(orderDTO.getCartItems())).thenReturn(amount);

        // Mocking token and username extraction
        String token = "Bearer mock-token";
        when(jwtService.extractUsername(token.substring(7))).thenReturn("testUser");

        // Mocking user retrieval
        User user = new User();
        when(userService.getUserByEmail("testUser")).thenReturn(Optional.of(user));

        // Mocking order save
        Order savedOrder = new Order();
        savedOrder.setId(1);
        when(orderService.saveOrder(any(Order.class))).thenReturn(savedOrder);

        // Calling the method under test
        ResponseEntity<ResponseOrderDTO> response = shoppingCartController.placeOrder(orderDTO, token);

        // Assertions
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        ResponseOrderDTO responseOrderDTO = response.getBody();
        assertThat(responseOrderDTO).isNotNull();

        // Assertions for the ResponseOrderDTO fields
        assertThat(responseOrderDTO.getAmount()).isEqualTo(amount);
        assertThat(responseOrderDTO.getOrderId()).isEqualTo(savedOrder.getId());
        assertThat(responseOrderDTO.getOrderDescription()).isEqualTo(orderDTO.getOrderDescription());
        assertThat(responseOrderDTO.getDate()).isNotNull();
        assertThat(responseOrderDTO.getInvoiceNumber()).isPositive();

        // Verify mock interactions
        // Verify that orderService.getCartAmount was called with the correct argument
        verify(orderService).getCartAmount(orderDTO.getCartItems());

        // Verify that jwtService.extractUsername was called with the correct argument
        verify(jwtService).extractUsername(token.substring(7));

        // Verify that userService.getUserByEmail was called with the correct argument
        verify(userService).getUserByEmail("testUser");

        // Verify that orderService.saveOrder was called with the correct order object
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderService).saveOrder(orderCaptor.capture());
        Order capturedOrder = orderCaptor.getValue();
        assertThat(capturedOrder.getOrderDescription()).isEqualTo(orderDTO.getOrderDescription());
        assertThat(capturedOrder.getUser()).isEqualTo(user);
        assertThat(capturedOrder.getCartItems()).isEqualTo(orderDTO.getCartItems());
    }

}
