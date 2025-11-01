package com.example.elisaappbe.service.userprogress;

import com.example.elisaappbe.dto.resp.UserProgressResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserProgressService {
    void completeLesson(Long userId, Long lessonId);
    Page<UserProgressResponse> getUserProgressList(String keyword, Pageable pageable);
}
