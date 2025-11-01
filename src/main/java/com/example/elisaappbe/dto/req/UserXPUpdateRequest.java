package com.example.elisaappbe.dto.req;

import lombok.Data;

@Data
public class UserXPUpdateRequest {
    private Long userId;
    private Integer xpToAdd; // Số XP muốn cộng thêm
}
