package com.example.elisaappbe.dto.req;

import lombok.Data;

@Data
public class UserProgressRequest {
    private Long userId;
    private Long lessonId;
}
