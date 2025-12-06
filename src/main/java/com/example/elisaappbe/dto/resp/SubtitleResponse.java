package com.example.elisaappbe.dto.resp;

import lombok.Data;

@Data
public class SubtitleResponse {
    private Long id;
    private Long startTime; // Mili-giây (VD: 1500)
    private Long endTime;   // Mili-giây
    private String content; // Nội dung câu thoại
}
