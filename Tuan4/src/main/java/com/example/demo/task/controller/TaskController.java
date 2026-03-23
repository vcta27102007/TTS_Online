package com.example.demo.task.controller;

import com.example.demo.task.dto.TaskRequest;
import com.example.demo.task.entity.TaskEntity;
import com.example.demo.task.service.TaskService;
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskEntity>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getByUser(userId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskEntity>> getByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getByProject(projectId));
    }

    @PostMapping
    public ResponseEntity<TaskEntity> create(@RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.create(request));
    }
}