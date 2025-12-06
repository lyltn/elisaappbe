package com.example.elisaappbe.service;

import com.example.elisaappbe.config.VttParser;
import com.example.elisaappbe.dto.req.EnglishVideoRequest;
import com.example.elisaappbe.dto.resp.EnglishVideoResponse;
import com.example.elisaappbe.dto.resp.SubtitleResponse;
import com.example.elisaappbe.dto.resp.VideoSummaryResponse;
import com.example.elisaappbe.model.EnglishSubtitle;
import com.example.elisaappbe.model.EnglishVideo;
import com.example.elisaappbe.repository.EnglishVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService {

    @Autowired
    private EnglishVideoRepository videoRepository;
    @Value("${tool.ytdlp.path}") private String ytDlpPath;


    private List<EnglishSubtitle> mergeDuplicateSubtitles(List<EnglishSubtitle> rawSubtitles) {
        if (rawSubtitles == null || rawSubtitles.isEmpty()) {
            return new ArrayList<>();
        }

        List<EnglishSubtitle> mergedList = new ArrayList<>();
        EnglishSubtitle previousSub = null;

        for (EnglishSubtitle currentSub : rawSubtitles) {
            // Chuẩn hóa nội dung để so sánh (xóa khoảng trắng thừa)
            String currentContent = currentSub.getContent() != null ? currentSub.getContent().trim() : "";

            // Kiểm tra nếu nội dung giống hệt câu trước đó
            if (previousSub != null && currentContent.equals(previousSub.getContent().trim())) {
                // Cập nhật end_time của câu trước thành end_time của câu hiện tại
                // (Tức là nối dài thời gian hiển thị ra)
                previousSub.setEndTime(currentSub.getEndTime());
            } else {
                // Nếu nội dung khác, thêm vào danh sách mới và đánh dấu là câu trước
                mergedList.add(currentSub);
                previousSub = currentSub;
            }
        }
        return mergedList;
    }

    public EnglishVideo importVideoFromUrl(EnglishVideoRequest req) throws Exception {
        // 1. Tạo tên file tạm thời
        String outputTemplate = "temp_sub_" + System.currentTimeMillis();

        // 2. Cấu hình lệnh: Tải sub format vtt, không tải video
        List<String> command = new ArrayList<>();
        command.add(ytDlpPath);
        command.add("--skip-download");
        command.add("--write-auto-sub");
        command.add("--sub-lang"); command.add("en");
        command.add("--sub-format"); command.add("vtt"); // Bắt buộc định dạng vtt
        command.add("-o"); command.add(outputTemplate); // Tên file đầu ra
        command.add(req.getUrl());

        ProcessBuilder pb = new ProcessBuilder(command);
        Process p = pb.start();
        p.waitFor();

        // 3. Tìm file vừa tải (yt-dlp thường thêm .en.vtt vào tên file)
        File vttFile = new File(outputTemplate + ".en.vtt");

        if (!vttFile.exists()) {
            throw new RuntimeException("Không tìm thấy sub! Có thể video không có sub tiếng Anh.");
        }

        // 4. Đọc nội dung file
        String content = Files.readString(vttFile.toPath());

        // 5. Dùng Parser để chuyển thành List Object
        List<EnglishSubtitle> rawSubtitles = VttParser.parse(content);

        // --- BƯỚC MỚI: Gộp các câu trùng lặp ---
        List<EnglishSubtitle> distinctSubtitles = mergeDuplicateSubtitles(rawSubtitles);
        // ---------------------------------------

        // 6. Lưu vào DB
        EnglishVideo video = new EnglishVideo();
        try {
            String videoId = extractVideoId(req.getUrl());
            video.setYoutubeId(videoId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Link YouTube không hợp lệ!");
        }

        // Gán relationship (Sử dụng list đã lọc: distinctSubtitles)
        for (EnglishSubtitle sub : distinctSubtitles) {
            sub.setEnglishVideo(video);
        }

        video.setTitle(req.getTitle());
        video.setLevel(req.getLevel());
        video.setType(req.getType());
        video.setSubtitles(distinctSubtitles); // Lưu list đã lọc

        // 7. Dọn dẹp file tạm
        vttFile.delete();

        return videoRepository.save(video);
    }

    public String extractVideoId(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL không được để trống");
        }

        // Regex này hỗ trợ:
        // 1. youtube.com/watch?v=ID
        // 2. youtu.be/ID
        // 3. youtube.com/embed/ID
        // 4. youtube.com/shorts/ID
        // 5. Link có chứa tham số khác (&t=...)
        String pattern = "^.*(?:youtu.be\\/|v\\/|u\\/\\w\\/|embed\\/|watch\\?v=|&v=|shorts\\/)([^#\\&\\?]*).*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);

        if (matcher.find() && matcher.group(1).length() == 11) {
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("Không tìm thấy YouTube Video ID hợp lệ từ URL: " + url);
        }
    }

    public EnglishVideoResponse getVideoDetails(Long videoId) {
        // 1. Lấy Entity từ DB
        EnglishVideo video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        // 2. Map sang DTO
        EnglishVideoResponse response = new EnglishVideoResponse();
        response.setId(video.getId());
        response.setYoutubeId(video.getYoutubeId());
        response.setTitle(video.getTitle());
        response.setLevel(video.getLevel());
        response.setType(video.getType());

        // 3. Map danh sách Subtitle và sắp xếp theo thời gian (quan trọng!)
        List<SubtitleResponse> subDtos = video.getSubtitles().stream()
                .map(sub -> {
                    SubtitleResponse dto = new SubtitleResponse();
                    dto.setId(sub.getId());
                    dto.setStartTime(sub.getStartTime());
                    dto.setEndTime(sub.getEndTime());
                    dto.setContent(sub.getContent());
                    return dto;
                })
                .sorted((s1, s2) -> s1.getStartTime().compareTo(s2.getStartTime())) // Sắp xếp tăng dần
                .collect(Collectors.toList());

        response.setSubtitles(subDtos);

        return response;
    }

    public List<VideoSummaryResponse> getAllVideos() {
        List<EnglishVideo> videos = videoRepository.findAll();

        // Convert từ Entity sang DTO rút gọn
        return videos.stream()
                .map(video -> new VideoSummaryResponse(
                        video.getId(),
                        video.getTitle(),
                        video.getYoutubeId(),
                        video.getLevel(),
                        video.getType()
                ))
                .collect(Collectors.toList());
    }

    public List<VideoSummaryResponse> getVideosByLevel(String level) {
        List<EnglishVideo> videos = videoRepository.findByLevel(level);

        // Convert từ Entity sang DTO rút gọn
        return videos.stream()
                .map(video -> new VideoSummaryResponse(
                        video.getId(),
                        video.getTitle(),
                        video.getYoutubeId(),
                        video.getLevel(),
                        video.getType()
                ))
                .collect(Collectors.toList());
    }

    public void deleteVideoById(Long id){
        Optional<EnglishVideo> getVideo = videoRepository.findById(id);
        if(getVideo.isPresent()){
            videoRepository.deleteById(id);
        }
    }

    public VideoSummaryResponse updateVideoById(Long id, EnglishVideoRequest req){
        Optional<EnglishVideo> getVideo = videoRepository.findById(id);
        if(getVideo.isPresent()){
            EnglishVideo updateVideo = getVideo.get();
            updateVideo.setTitle(req.getTitle());
            updateVideo.setType(req.getType());
            updateVideo = videoRepository.save(updateVideo);
            return new VideoSummaryResponse(
                    updateVideo.getId(),
                    updateVideo.getTitle(),
                    updateVideo.getYoutubeId(),
                    updateVideo.getLevel(),
                    updateVideo.getType()
                    );
        }
        return null;
    }
}

