package com.example.mvc_teamwork.repository;

import com.example.mvc_teamwork.entity.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoRepository extends JpaRepository<Promo,Integer> {
    Optional<Promo> findByCode(String code);
}
