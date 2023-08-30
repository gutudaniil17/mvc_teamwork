package com.example.mvc_teamwork.controller;

import com.example.mvc_teamwork.entity.*;
import com.example.mvc_teamwork.security.config.JwtService;
import com.example.mvc_teamwork.security.entity.User;
import com.example.mvc_teamwork.service.order.OrderService;
import com.example.mvc_teamwork.service.product.ProductService;
import com.example.mvc_teamwork.service.user.UserService;
import com.example.mvc_teamwork.service.user.UserServiceImpl;
import com.example.mvc_teamwork.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;
    private final JwtService jwtService;

    private Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> productList = productService.getAllProducts();

        return ResponseEntity.ok(productList);
    }

    @GetMapping(value = "/user/getOrder/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int orderId) {

        Order order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }


    @PostMapping("/user/placeOrder")
    public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO orderDTO, @RequestHeader("Authorization") String token) {
        logger.info("Request Payload " + orderDTO.toString());
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
        float amount = orderService.getCartAmount(orderDTO.getCartItems());

        token = token.substring(7);
        logger.info(token);
        String username = jwtService.extractUsername(token);
        User user = userService.getUserByEmail(username).get();

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
    @GetMapping("/user/download_order_info/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable int id) throws IOException {
        orderService.fillReportToUser(id);
        Resource resource = new UrlResource("file:src/main/resources/jasper-templates/OrderReportUser.jrxml");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "OrderReportUser.pdf");
        byte[] fileBytes = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .headers(headers)
                .body(fileBytes);
    }
}
