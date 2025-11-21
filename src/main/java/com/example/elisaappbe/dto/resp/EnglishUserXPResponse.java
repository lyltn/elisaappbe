package com.example.elisaappbe.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishUserXPResponse {
    private long userId;
    private long achievementsID;
    private String title;
    private String description;
    private String icon_url;
    private long totalXP;
}
