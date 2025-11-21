package com.example.elisaappbe.service.englishUserXP;

import com.example.elisaappbe.dto.req.EnglishUserXPRequest;
import com.example.elisaappbe.dto.resp.EnglishUserXPResponse;

public interface EnglishUserXPService {
    EnglishUserXPResponse getUserXP(long userId);
    EnglishUserXPResponse updateUserXP(EnglishUserXPRequest res);
    EnglishUserXPResponse createUserXP(long userId);
}
