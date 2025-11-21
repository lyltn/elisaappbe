package com.example.elisaappbe.dto.req;

import lombok.Data;

@Data
public class EnglishUserProgressRequest {
    private Long userId;
    private Long lessonId;
    private Long section;
}
