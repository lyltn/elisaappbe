package com.example.elisaappbe.service.userxp;

import com.example.elisaappbe.dto.req.UserXPUpdateRequest;
import com.example.elisaappbe.dto.resp.LeaderboardResponse;
import com.example.elisaappbe.dto.resp.UserXPResponse;

import java.util.List;

public interface UserXPService {
    UserXPResponse getUserXP(Long userId);
    UserXPResponse addXP(UserXPUpdateRequest req);
    UserXPResponse createInitialUserXP(Long userId);
    List<LeaderboardResponse> getLeaderboard();

}