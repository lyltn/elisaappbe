package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnglishNotificationRepository extends JpaRepository<EnglishNotification, Long> {
    List<EnglishNotification> findByUser_UserId(Long userId);

    @Modifying
    @Query("UPDATE EnglishNotification n SET n.isRead = true WHERE n.user.userId = :userId AND n.isRead = false")
    void markAllAsRead(@Param("userId") Long userId);
}
