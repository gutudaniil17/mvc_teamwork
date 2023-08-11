package com.example.mvc_teamwork;

import com.example.mvc_teamwork.security.controller.AuthenticationService;
import com.example.mvc_teamwork.security.controller.RegisterRequest;
import com.example.mvc_teamwork.security.entity.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MvcTeamworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcTeamworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService service) {
        return args -> {
            var ROLE_ADMIN = RegisterRequest.builder().firstName("ROLE_ADMIN")
                    .lastName("ROLE_ADMIN").email("ROLE_ADMIN@mail.com")
                    .password("password").role(Role.ROLE_ADMIN)
                    .build();
            System.out.println("ROLE_ADMIN token: " + service.register(ROLE_ADMIN).getAccessToken());
        };
    }
}

