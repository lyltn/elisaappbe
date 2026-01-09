package com.example.elisaappbe.dto.resp;

import lombok.Data;

@Data
public class AuthResponse {
    private Long userId;
    private String email;
    private String fullName;
    private String token;
    private String role;
    // private String status;
}
