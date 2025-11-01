package com.example.elisaappbe.dto.req;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleSignInRequest {
    private String googleToken;
}