package com.example.elisaappbe.dto.resp;

import lombok.Data;

@Data
public class VideoSummaryResponse {
    private Long id;
    private String title;
    private String youtubeId;
    private String level;
    private String type;
    private String thumbnailUrl; // Link ảnh đại diện video

    // Constructor để convert nhanh
    public VideoSummaryResponse(Long id, String title, String youtubeId, String level, String type) {
        this.id = id;
        this.title = title;
        this.youtubeId = youtubeId;
        this.level = level;
        this.type = type;
        // YouTube có chuẩn link ảnh thumb: img.youtube.com/vi/[VIDEO_ID]/mqdefault.jpg
        this.thumbnailUrl = "https://img.youtube.com/vi/" + youtubeId + "/mqdefault.jpg";
    }
}
