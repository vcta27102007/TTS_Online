package com.example.demo.task.repository;

import com.example.demo.task.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByUserId(Long userId);

    List<TaskEntity> findByProjectId(Long projectId);
}
