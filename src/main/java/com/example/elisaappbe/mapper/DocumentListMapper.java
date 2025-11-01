// src/main/java/org/example/ktigerstudybe/mapper/DocumentListMapper.java
package com.example.elisaappbe.mapper;


import com.example.elisaappbe.dto.resp.DocumentListResponse;
import com.example.elisaappbe.model.DocumentList;
import org.springframework.stereotype.Component;

@Component
public class DocumentListMapper {

    /**
     * Chuyển đổi từ DocumentList entity sang DocumentListResponse DTO.
     */
    public DocumentListResponse toResponse(DocumentList entity) {
        if (entity == null) {
            return null;
        }

        DocumentListResponse dto = new DocumentListResponse();
        dto.setListId(entity.getListId());
        dto.setUserId(entity.getUser().getUserId());
        dto.setFullName(entity.getUser().getFullName());
        dto.setAvatarImage(entity.getUser().getAvatarImage());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setType(entity.getType());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setIsPublic(entity.getIsPublic());
        return dto;
    }
}
