package com.example.mvc_teamwork.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {

    @GetMapping("api/v1/auth/demo-controller")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/api/v1/auth/admin/hello") ResponseEntity<String> sayHelloForAdmin(){
        return ResponseEntity.ok("hello, admin");
    }
}
