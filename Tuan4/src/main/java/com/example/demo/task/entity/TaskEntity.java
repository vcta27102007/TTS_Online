package com.example.demo.task.entity;
import com.example.demo.project.entity.ProjectEntity;
import com.example.demo.user.entity.UserEntity;
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

    private String trangThai;

    private LocalDateTime deadline;
    private LocalDateTime ngayTao;
    private LocalDateTime ngayCapNhat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_duoc_giao")
    private UserEntity user;

    @PrePersist
    public void prePersist() {
        ngayTao = LocalDateTime.now();
        ngayCapNhat = LocalDateTime.now();
        if (trangThai == null) {
            trangThai = "CHUA_LAM";
        }
    }

}
