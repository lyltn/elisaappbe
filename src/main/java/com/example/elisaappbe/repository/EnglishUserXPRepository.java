package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishUserXP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnglishUserXPRepository extends JpaRepository<EnglishUserXP, Long> {
    Optional<EnglishUserXP> findByUser_UserId(Long userId);
    @Query("SELECT e FROM EnglishUserXP e JOIN FETCH e.user LEFT JOIN FETCH e.currentAchievement ORDER BY e.totalXP DESC")
    List<EnglishUserXP> findAllWithDetails();
}
