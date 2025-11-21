package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishUserXP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnglishUserXPRepository extends JpaRepository<EnglishUserXP, Long> {
    Optional<EnglishUserXP> findByUser_UserId(Long userId);
}
