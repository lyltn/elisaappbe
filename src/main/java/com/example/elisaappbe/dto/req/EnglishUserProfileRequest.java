package com.example.elisaappbe.dto.req;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EnglishUserProfileRequest {
    private Long userId;
    private String fullName;
    private String email;
    private String gender;
    private LocalDate dateOfBirth;
}
