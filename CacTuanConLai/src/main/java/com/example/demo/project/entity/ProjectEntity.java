package com.example.demo.project.entity;

import com.example.demo.task.entity.TaskEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_project", nullable = false)
    private String tenProject;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTenProject() {
        return tenProject;
    }

    public void setTenProject(String tenProject) {
        this.tenProject = tenProject;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }
}