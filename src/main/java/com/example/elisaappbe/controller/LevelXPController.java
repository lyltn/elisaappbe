package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.req.LevelXPRequest;
import com.example.elisaappbe.dto.resp.LevelXPResponse;
import com.example.elisaappbe.service.levelxp.LevelXPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/level-xp")
public class LevelXPController {

    @Autowired
    private LevelXPService service;

    @GetMapping
    public List<LevelXPResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{levelNumber}")
    public LevelXPResponse getByLevelNumber(@PathVariable Integer levelNumber) {
        return service.getByLevelNumber(levelNumber);
    }

    @PostMapping
    public LevelXPResponse createOrUpdate(@RequestBody LevelXPRequest req) {
        return service.createOrUpdate(req);
    }

    @DeleteMapping("/{levelNumber}")
    public void deleteByLevelNumber(@PathVariable Integer levelNumber) {
        service.deleteByLevelNumber(levelNumber);
    }
}