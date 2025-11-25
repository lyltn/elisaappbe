package com.example.elisaappbe.service.cloud;

import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CloudinaryService {
    @NonFinal
    @Value("${cloudinary.unsigned-upload-preset}")
    protected String namePresets;

    Cloudinary cloudinary;

    public String uploadFile(MultipartFile file, String publicId) throws IOException {
        try {
            // "upload_preset" rất quan trọng cho việc tải lên không có chữ ký
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("upload_preset", namePresets,
                            "resource_type", "auto",
                            "public_id", publicId,
                            "overwrite", true));

            // Trả về URL của file đã tải lên
            log.error("Upload ảnh thành công!!!");
            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            log.error("Lỗi khi tải file lên Cloudinary: {}", e.getMessage(), e);
            // Xử lý lỗi tải lên (ví dụ: ghi log, ném một ngoại lệ tùy chỉnh)
            throw new RuntimeException("Lỗi upload file lên Cloudinary: " + e.getMessage());
        }
    }
}