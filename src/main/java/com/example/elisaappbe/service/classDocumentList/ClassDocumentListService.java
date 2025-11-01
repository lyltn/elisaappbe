package com.example.elisaappbe.service.classDocumentList;

import com.example.elisaappbe.dto.req.ClassDocumentListRequest;
import com.example.elisaappbe.dto.resp.ClassDocumentListResponse;

import java.util.List;

public interface ClassDocumentListService {
    List<ClassDocumentListResponse> getAll();
    ClassDocumentListResponse getById(Long id);
    ClassDocumentListResponse create(ClassDocumentListRequest request);
    void delete(Long id);
    List<ClassDocumentListResponse> getByClassId(Long classId);
    List<ClassDocumentListResponse> getByListId(Long listId);
}
