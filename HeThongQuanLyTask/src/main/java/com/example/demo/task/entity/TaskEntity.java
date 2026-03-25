package com.example.demo.task.entity;

import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.task.enums.TaskStatus;
import com.example.demo.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tieuDe;
    private String moTa;

    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    private TaskStatus trangThai;

    @ManyToOne
    @JoinColumn(name = "user_duoc_giao")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private ProjectEntity project;

}