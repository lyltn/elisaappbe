package com.example.elisaappbe.service.lesson;

import com.example.elisaappbe.dto.req.LessonRequest;
import com.example.elisaappbe.dto.resp.LessonResponse;
import com.example.elisaappbe.dto.resp.LessonWithProgressResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface LessonService {
    LessonResponse createLesson(LessonRequest request);
    LessonResponse updateLesson(Long Id, LessonRequest request);
    List<LessonResponse> getAllLessons();
    LessonResponse getLessonById(Long Id);
    void deleteLesson(Long Id);

    List<LessonResponse> getLessonsByLevelId(Long levelId);

    List<LessonWithProgressResponse> getLessonsWithProgress(Long levelId, Long userId);

    Map<String, Object> completeLesson(Long userId, Long lessonId, Integer score);
    //Admin
    // NEW: API phân trang, tìm kiếm
    Page<LessonResponse> getLessons(
            int page,
            int size,
            Long levelId,
            String keyword
    );
}
