package com.example.demo.task.controller;

import com.example.demo.common.response.ApiResponse;
import com.example.demo.task.dto.TaskRequest;
import com.example.demo.task.entity.TaskEntity;
import com.example.demo.task.enums.TaskStatus;
import com.example.demo.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskEntity>> create(@Valid @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Tạo task thành công", taskService.create(request)));
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<ApiResponse<TaskEntity>> assign(@PathVariable Long taskId,
                                                          @PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.success("Assign task thành công", taskService.assign(taskId, userId)));
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<ApiResponse<TaskEntity>> updateStatus(@PathVariable Long taskId,
                                                                @RequestParam TaskStatus status) {
        return ResponseEntity.ok(ApiResponse.success("Cập nhật trạng thái task thành công", taskService.updateStatus(taskId, status)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<TaskEntity>>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách task theo user thành công", taskService.getByUser(userId)));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<TaskEntity>>> getByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách task theo project thành công", taskService.getByProject(projectId)));
    }
}
