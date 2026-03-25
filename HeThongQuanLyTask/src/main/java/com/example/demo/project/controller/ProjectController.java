package com.example.demo.project.controller;

import com.example.demo.common.response.ApiResponse;
import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectEntity>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách project thành công", projectService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectEntity>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Lấy project thành công", projectService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectEntity>> create(@RequestBody ProjectEntity request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Tạo project thành công", projectService.create(request)));
    }
}
