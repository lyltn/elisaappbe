package com.example.elisaappbe.dto.req;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String email;
    private String currentPassword;
    private String newPassword;
}