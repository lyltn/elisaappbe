package com.example.elisaappbe.service.classUser;

import com.example.elisaappbe.dto.req.ClassUserRequest;
import com.example.elisaappbe.dto.resp.ClassUserResponse;

import java.util.List;

public interface ClassUserService {
    List<ClassUserResponse> getAll();
    ClassUserResponse getById(Long id);
    ClassUserResponse create(ClassUserRequest request);
    void delete(Long id);

    void deleteByUser(Long userId);

    List<ClassUserResponse> getByClass(Long classId);
    List<ClassUserResponse> getByUser(Long userId);
}
