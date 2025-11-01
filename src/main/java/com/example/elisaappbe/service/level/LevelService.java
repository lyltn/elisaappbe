package com.example.elisaappbe.service.level;


import com.example.elisaappbe.dto.req.LevelRequest;
import com.example.elisaappbe.dto.resp.LevelResponse;

import java.util.List;

public interface LevelService {
    List<LevelResponse> getAllLevels();
    LevelResponse getLevelById(Long id);
    LevelResponse createLevel(LevelRequest request);
    LevelResponse updateLevel(Long id, LevelRequest request);
    void deleteLevel(Long id);
}