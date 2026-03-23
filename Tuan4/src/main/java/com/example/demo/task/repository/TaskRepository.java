package com.example.demo.task.repository;

import com.example.demo.task.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByUser_Id(Long userId);

    List<TaskEntity> findByProject_Id(Long projectId);

    List<TaskEntity> findByTrangThai(String trangThai);
}
