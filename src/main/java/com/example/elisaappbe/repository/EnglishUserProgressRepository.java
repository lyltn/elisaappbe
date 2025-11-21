package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishUserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnglishUserProgressRepository extends JpaRepository<EnglishUserProgress, Long> {
    Optional<EnglishUserProgress> findByUser_UserId(Long userId);
}
