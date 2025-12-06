package com.example.elisaappbe.dto.resp;

import lombok.Data;

import java.util.List;

@Data
public class EnglishVideoResponse {
    private Long id;            // ID video trong DB của bạn
    private String youtubeId;   // ID Youtube (VD: dQw4w9WgXcQ) để player chạy
    private String title;       // Tiêu đề video
    private String level;
    private String type;
    private List<SubtitleResponse> subtitles; // Danh sách sub đã sort theo thời gian
}
