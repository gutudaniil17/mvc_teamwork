package com.example.mvc_teamwork.service.user;


import com.example.mvc_teamwork.security.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<Integer> isCustomerPresent(User user);
    public User saveUser(User user);
    public Optional<User> getUserByEmail(String email);

}
