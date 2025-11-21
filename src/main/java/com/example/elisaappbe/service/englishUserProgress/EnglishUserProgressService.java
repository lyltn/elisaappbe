package com.example.elisaappbe.service.englishUserProgress;

import com.example.elisaappbe.dto.req.EnglishUserProgressRequest;
import com.example.elisaappbe.dto.resp.EnglishUserProgressResponse;

public interface EnglishUserProgressService {
    EnglishUserProgressResponse getUserProgress(long userId);
    EnglishUserProgressResponse updateProgress(EnglishUserProgressRequest res);
    EnglishUserProgressResponse createUserProgress(long userId);
}
