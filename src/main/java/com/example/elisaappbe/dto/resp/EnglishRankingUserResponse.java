package com.example.elisaappbe.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishRankingUserResponse {
    private long userId;
    private String fullName;
    private String avatarImage;
    private long achievementsID;
    private String title;
    private String description;
    private String icon_url;
    private long totalXP;
}
