package com.example.mvc_teamwork.controller;

import com.example.mvc_teamwork.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class Demo {

    private final ProductRepository productRepository;

    @GetMapping("/user/demo-controller")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/admin/hello")
    public ResponseEntity<String> sayHelloForAdmin(){
        return ResponseEntity.ok("hello, admin");
    }
}
