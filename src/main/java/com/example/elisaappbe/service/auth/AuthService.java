package com.example.elisaappbe.service.auth;

import com.example.elisaappbe.dto.req.GoogleSignInRequest;
import com.example.elisaappbe.dto.req.SignInRequest;
import com.example.elisaappbe.dto.req.SignUpRequest;
import com.example.elisaappbe.dto.resp.AuthResponse;
import com.example.elisaappbe.dto.resp.GoogleSignInResponse;

public interface AuthService {
    AuthResponse signUp(SignUpRequest request);
    AuthResponse signIn(SignInRequest request);
    void forgotPassword(String email);
    void resetPassword(String token, String newPassword);

    GoogleSignInResponse googleSignIn(GoogleSignInRequest request);

}
