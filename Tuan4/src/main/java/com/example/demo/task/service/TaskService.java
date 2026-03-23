package com.example.demo.task.service;

import com.example.demo.common.exception.NotFoundException;
import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.repository.ProjectRepository;
import com.example.demo.task.dto.TaskRequest;
import com.example.demo.task.entity.TaskEntity;
import com.example.demo.task.repository.TaskRepository;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository,
                       UserRepository userRepository,
                       ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public List<TaskEntity> getByUser(Long userId) {
        return taskRepository.findByUser_Id(userId);
    }

    public List<TaskEntity> getByProject(Long projectId) {
        return taskRepository.findByProject_Id(projectId);
    }

    public TaskEntity create(TaskRequest request) {

        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User không tồn tại"));

        ProjectEntity project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new NotFoundException("Project không tồn tại"));

        TaskEntity task = new TaskEntity();
        task.setTieuDe(request.getTieuDe());
        task.setMoTa(request.getMoTa());
        task.setDeadline(request.getDeadline());
        task.setUser(user);
        task.setProject(project);

        return taskRepository.save(task);
    }
}
