package com.example.demo.task.service;

import com.example.demo.common.exception.BadRequestException;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.project.repository.ProjectRepository;
import com.example.demo.task.dto.TaskRequest;
import com.example.demo.task.entity.TaskEntity;
import com.example.demo.task.enums.TaskStatus;
import com.example.demo.task.repository.TaskRepository;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public TaskEntity create(TaskRequest request) {
        validateBusinessRule(request);

        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User không tồn tại"));

        ProjectEntity project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new NotFoundException("Project không tồn tại"));

        TaskEntity task = new TaskEntity();
        task.setTieuDe(request.getTieuDe().trim());
        task.setMoTa(request.getMoTa() != null ? request.getMoTa().trim() : null);
        task.setDeadline(request.getDeadline());
        task.setUser(user);
        task.setProject(project);
        task.setTrangThai(TaskStatus.CHUA_LAM);

        return taskRepository.save(task);
    }

    public TaskEntity assign(Long taskId, Long userId) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task không tồn tại"));

        if (task.getTrangThai() == TaskStatus.HOAN_THANH) {
            throw new BadRequestException("Task đã hoàn thành, không thể assign");
        }

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User không tồn tại"));

        task.setUser(user);
        return taskRepository.save(task);
    }

    public TaskEntity updateStatus(Long taskId, TaskStatus newStatus) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task không tồn tại"));

        if (newStatus == null) {
            throw new BadRequestException("Status không được để trống");
        }

        if (task.getTrangThai() == TaskStatus.HOAN_THANH) {
            throw new BadRequestException("Task đã hoàn thành, không được update");
        }

        if (task.getTrangThai() == TaskStatus.CHUA_LAM && newStatus == TaskStatus.HOAN_THANH) {
            throw new BadRequestException("Phải chuyển sang DANG_LAM trước");
        }

        task.setTrangThai(newStatus);
        return taskRepository.save(task);
    }

    public List<TaskEntity> getByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User không tồn tại");
        }
        return taskRepository.findByUser_Id(userId);
    }

    public List<TaskEntity> getByProject(Long projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new NotFoundException("Project không tồn tại");
        }
        return taskRepository.findByProject_Id(projectId);
    }

    private void validateBusinessRule(TaskRequest request) {
        if (request.getDeadline() != null && !request.getDeadline().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Deadline phải lớn hơn thời điểm hiện tại");
        }
    }

    public List<TaskEntity> getMyTasks(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user với email = " + email));
        return taskRepository.findByUser_Id(user.getId());
    }
}
