package com.example.demo.user.dto;

import java.time.LocalDateTime;

public class UserResponse {
    private Long id;
    private String ten;
    private String email;
    private String role;
    private Boolean dangHoatDong;
    private LocalDateTime createdAt;

    public UserResponse() {
    }

    public UserResponse(Long id, String ten, String email, String role, Boolean dangHoatDong, LocalDateTime createdAt) {
        this.id = id;
        this.ten = ten;
        this.email = email;
        this.role = role;
        this.dangHoatDong = dangHoatDong;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Boolean getDangHoatDong() {
        return dangHoatDong;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}