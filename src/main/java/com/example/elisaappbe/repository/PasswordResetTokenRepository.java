package com.example.elisaappbe.repository;


import com.example.elisaappbe.model.PasswordResetToken;
import com.example.elisaappbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    void deleteByUser(User user);
}