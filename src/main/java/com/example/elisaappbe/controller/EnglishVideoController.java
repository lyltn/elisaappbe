package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.req.EnglishVideoRequest;
import com.example.elisaappbe.dto.resp.EnglishVideoResponse;
import com.example.elisaappbe.dto.resp.VideoSummaryResponse;
import com.example.elisaappbe.model.EnglishVideo;
import com.example.elisaappbe.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/videos")
public class EnglishVideoController {
    @Autowired
    private VideoService videoService;

    // getAll
    @GetMapping("/get-list") // Đường dẫn sẽ là: GET /api/videos
    public ResponseEntity<List<VideoSummaryResponse>> getAllVideos() {
        List<VideoSummaryResponse> videos = videoService.getAllVideos();
        return ResponseEntity.ok(videos);
    }

    // getByLevel
    @GetMapping("/")
    public ResponseEntity<List<VideoSummaryResponse>> getVideosByLevel(@RequestParam String level) {
        System.out.println("===== "+level + "=====");
        List<VideoSummaryResponse> videos = videoService.getVideosByLevel(level);
        return ResponseEntity.ok(videos);
    }

    // getById
    @GetMapping("/{id}")
    public ResponseEntity<EnglishVideoResponse> getVideoById(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.getVideoDetails(id));
    }

    @PostMapping("/create")
    public ResponseEntity<EnglishVideo> importVideo(@RequestBody EnglishVideoRequest req) {
        try {
            EnglishVideo video = videoService.importVideoFromUrl(req);
            return ResponseEntity.ok(video);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VideoSummaryResponse> updateVideoById(@PathVariable Long id,
                                                                @RequestBody EnglishVideoRequest req){
        return ResponseEntity.ok(videoService.updateVideoById(id, req));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable Long id){
        videoService.deleteVideoById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa video thành công");
        response.put("deletedId", id);
        return ResponseEntity.ok(response);
    }



}