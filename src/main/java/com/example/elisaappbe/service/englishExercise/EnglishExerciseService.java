package com.example.elisaappbe.service.englishExercise;

import com.example.elisaappbe.dto.resp.EnglishExerciseResponse;

public interface EnglishExerciseService {
    EnglishExerciseResponse getExerciseByLesson(long id);
}
