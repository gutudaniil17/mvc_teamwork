package com.example.mvc_teamwork.controller;

import com.example.mvc_teamwork.entity.*;
import com.example.mvc_teamwork.security.entity.User;
import com.example.mvc_teamwork.service.order.OrderService;
import com.example.mvc_teamwork.service.product.ProductService;
import com.example.mvc_teamwork.service.user.UserService;
import com.example.mvc_teamwork.service.user.UserServiceImpl;
import com.example.mvc_teamwork.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("api/v1/auth/user/shopping_cart")
public class ShoppingCartController {

    private OrderService orderService;
    private ProductService productService;
    private UserService userService;

       public ShoppingCartController(OrderService orderService, ProductService productService, UserServiceImpl userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    private Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> productList = productService.getAllProducts();

        return ResponseEntity.ok(productList);
    }

    @GetMapping(value = "/getOrder/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int orderId) {

        Order order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }


    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Request Payload " + orderDTO.toString());
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
        float amount = orderService.getCartAmount(orderDTO.getCartItems());

        User user = new User();
        user.setFirstName(orderDTO.getUserFirstName());
        user.setEmail(orderDTO.getUserEmail());

        Optional<Integer> userIdOptional = userService.isCustomerPresent(user);
        if (userIdOptional.isPresent()) {
            Integer userIdFromDb = userIdOptional.get();
            user.setId(userIdFromDb);
            logger.info("User already present in db with id: " + userIdFromDb);
        } else {
            user = userService.saveUser(user);
            logger.info("User saved with id: " + user.getId());
        }

        Order order = new Order();
        order.setOrderDescription(orderDTO.getOrderDescription());
        order.setUser(user);
        order.setCartItems(orderDTO.getCartItems());
        order = orderService.saveOrder(order);
        logger.info("Order processed successfully..");

        responseOrderDTO.setAmount(amount);
        responseOrderDTO.setDate(DateUtil.getCurrentDateTime());
        responseOrderDTO.setInvoiceNumber(new Random().nextInt(1000));
        responseOrderDTO.setOrderId(order.getId());
        responseOrderDTO.setOrderDescription(orderDTO.getOrderDescription());

        logger.info("test push..");

        return ResponseEntity.ok(responseOrderDTO);
    }
}
