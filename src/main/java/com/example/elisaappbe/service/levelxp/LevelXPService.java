package com.example.elisaappbe.service.levelxp;

import com.example.elisaappbe.dto.req.LevelXPRequest;
import com.example.elisaappbe.dto.resp.LevelXPResponse;

import java.util.List;

public interface LevelXPService {
    LevelXPResponse createOrUpdate(LevelXPRequest req);
    List<LevelXPResponse> getAll();
    LevelXPResponse getByLevelNumber(Integer levelNumber);
    void deleteByLevelNumber(Integer levelNumber);
}
