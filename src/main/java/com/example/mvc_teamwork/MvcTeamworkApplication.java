package com.example.mvc_teamwork;

import com.example.mvc_teamwork.security.controller.AuthenticationService;
import com.example.mvc_teamwork.security.controller.RegisterRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

import static com.example.mvc_teamwork.security.entity.Role.ADMIN;

@SpringBootApplication
public class MvcTeamworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(MvcTeamworkApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner commandLineRunner(AuthenticationService service) {
        return args -> {
            var admin = RegisterRequest.builder().firstName("Admin")
                    .lastName("Admin").email("admin@mail.com")
                    .password("password").role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());
        };
    }*/

    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}

