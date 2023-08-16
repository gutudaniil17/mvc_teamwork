package com.example.mvc_teamwork.security.repository;

import com.example.mvc_teamwork.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> getUserByEmailAndFirstName(String email, String firstName);
    List<User> findAllByEmail(String email);

}
