package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.req.ClassRequest;
import com.example.elisaappbe.dto.resp.ClassResponse;
import com.example.elisaappbe.service.classService.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping
    public List<ClassResponse> getAll() {
        return classService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResponse> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(classService.getClassById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ClassResponse create(@RequestBody ClassRequest request) {
        return classService.createClass(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassResponse> update(@PathVariable Long id, @RequestBody ClassRequest request) {
        try {
            return ResponseEntity.ok(classService.updateClass(id, request));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public List<ClassResponse> getByUser(@PathVariable Long userId) {
        return classService.getClassesByUserId(userId);
    }
}
