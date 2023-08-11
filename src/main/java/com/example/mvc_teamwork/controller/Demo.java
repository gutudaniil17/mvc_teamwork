package com.example.mvc_teamwork.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class Demo {

    @GetMapping("/user/demo-controller")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/admin/hello") ResponseEntity<String> sayHelloForROLE_ADMIN(){
        return ResponseEntity.ok("hello, ROLE_ADMIN");
    }
}
