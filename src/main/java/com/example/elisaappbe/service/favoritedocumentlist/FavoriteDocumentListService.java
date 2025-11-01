package com.example.elisaappbe.service.favoritedocumentlist;

import com.example.elisaappbe.dto.req.FavoriteDocumentListRequest;
import com.example.elisaappbe.dto.resp.FavoriteDocumentListResponse;

import java.util.List;
import java.util.Optional;

public interface FavoriteDocumentListService {
    List<FavoriteDocumentListResponse> getAll();
    FavoriteDocumentListResponse getById(Long id);
    FavoriteDocumentListResponse create(FavoriteDocumentListRequest request);
    void delete(Long id);
    List<FavoriteDocumentListResponse> getByUser(Long userId);
    List<FavoriteDocumentListResponse> getByList(Long listId);
    Optional<FavoriteDocumentListResponse> getByUserAndList(Long userId, Long listId);
}
