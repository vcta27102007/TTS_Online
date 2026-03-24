package com.example.demo.auth.dto;

public class AuthResponse {
    private String token;
    private String email;
    private String role;
    private String ten;

    public AuthResponse(String token, String email, String role, String ten) {
        this.token = token;
        this.email = email;
        this.role = role;
        this.ten = ten;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getTen() {
        return ten;
    }
}