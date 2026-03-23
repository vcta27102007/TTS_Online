package com.example.demo.user.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten", nullable = false, length = 100)
    private String ten;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "role", nullable = false, length = 30)
    private String role;

    @Column(name = "dang_hoat_dong", nullable = false)
    private Boolean dangHoatDong;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public UserEntity() {
    }

    public UserEntity(Long id, String ten, String email, String role, Boolean dangHoatDong, LocalDateTime createdAt) {
        this.id = id;
        this.ten = ten;
        this.email = email;
        this.role = role;
        this.dangHoatDong = dangHoatDong;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (dangHoatDong == null) {
            dangHoatDong = true;
        }
    }

    public Long getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getDangHoatDong() {
        return dangHoatDong;
    }

    public void setDangHoatDong(Boolean dangHoatDong) {
        this.dangHoatDong = dangHoatDong;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}