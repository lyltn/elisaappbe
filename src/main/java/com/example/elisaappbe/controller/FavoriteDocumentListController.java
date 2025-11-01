package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.req.FavoriteDocumentListRequest;
import com.example.elisaappbe.dto.resp.FavoriteDocumentListResponse;
import com.example.elisaappbe.service.favoritedocumentlist.FavoriteDocumentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite-lists")
public class FavoriteDocumentListController {

    @Autowired
    private FavoriteDocumentListService service;

    @GetMapping
    public List<FavoriteDocumentListResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDocumentListResponse> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public FavoriteDocumentListResponse create(@RequestBody FavoriteDocumentListRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public List<FavoriteDocumentListResponse> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    @GetMapping("/list/{listId}")
    public List<FavoriteDocumentListResponse> getByList(@PathVariable Long listId) {
        return service.getByList(listId);
    }

    @GetMapping("/user/{userId}/list/{listId}")
    public ResponseEntity<FavoriteDocumentListResponse> checkFavorite(
            @PathVariable Long userId,
            @PathVariable Long listId
    ) {
        return service.getByUserAndList(userId, listId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
