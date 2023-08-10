package com.example.mvc_teamwork;

import com.example.mvc_teamwork.security.controller.AuthenticationService;
import com.example.mvc_teamwork.security.controller.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.mvc_teamwork.security.entity.user.Role.ADMIN;

@SpringBootApplication
public class MvcTeamworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcTeamworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService service) {
        return args -> {
            var admin = RegisterRequest.builder().firstName("Admin")
                    .lastName("Admin").email("admin@mail.com")
                    .password("password").role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());
        };
    }
}

