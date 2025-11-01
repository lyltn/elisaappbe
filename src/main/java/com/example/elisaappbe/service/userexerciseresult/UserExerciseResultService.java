package com.example.elisaappbe.service.userexerciseresult;

import com.example.elisaappbe.dto.req.UserExerciseResultRequest;
import com.example.elisaappbe.dto.resp.UserExerciseResultResponse;
import com.example.elisaappbe.model.UserExerciseResult;

import java.util.List;

public interface UserExerciseResultService {
    UserExerciseResultResponse saveResult(UserExerciseResultRequest req);
    List<UserExerciseResultResponse> getResultsByUserId(Long userId);
    UserExerciseResultResponse getResultByUserIdAndExerciseId(Long userId, Long exerciseId);
}
