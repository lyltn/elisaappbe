package com.example.elisaappbe.service.classService;

import com.example.elisaappbe.dto.req.ClassRequest;
import com.example.elisaappbe.dto.resp.ClassResponse;

import java.util.List;

public interface ClassService {
    List<ClassResponse> getAllClasses();
    ClassResponse getClassById(Long classId);
    ClassResponse createClass(ClassRequest request);
    ClassResponse updateClass(Long classId, ClassRequest request);
    void deleteClass(Long classId);
    List<ClassResponse> getClassesByUserId(Long userId);
    ClassResponse getClassByIdAndPassword(Long classId, String password);

}
