package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.req.UserXPUpdateRequest;
import com.example.elisaappbe.dto.resp.LeaderboardResponse;
import com.example.elisaappbe.dto.resp.UserXPResponse;
import com.example.elisaappbe.service.userxp.UserXPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-xp")
public class UserXPController {

    @Autowired
    private UserXPService userXPService;

    @GetMapping("/{userId}")
    public UserXPResponse getUserXP(@PathVariable Long userId) {
        return userXPService.getUserXP(userId);
    }

    @PostMapping("/add")
    public UserXPResponse addXP(@RequestBody UserXPUpdateRequest req) {
        return userXPService.addXP(req);
    }
    @GetMapping("/leaderboard")
    public List<LeaderboardResponse> getLeaderboard() {
        return userXPService.getLeaderboard();
    }
}
