package com.example.mvc_teamwork.repository;

import com.example.mvc_teamwork.entity.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PromoRepository extends JpaRepository<Promo,Integer> {
    Optional<Promo> findByCode(String code);
}
