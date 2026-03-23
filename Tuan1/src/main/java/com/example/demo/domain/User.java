package com.example.demo.domain;

import com.example.demo.util.Validators;

public class User {
    private final long id;
    private String ten;
    private String email;
    private Role role;
    private boolean dangHoatDong;

    public User(long id, String ten, String email, Role role) {
        Validators.soDuong(id, "User.id");
        Validators.khongDuocTrong(ten, "Tên user");
        Validators.emailHopLeHoacNull(email);

        this.id = id;
        this.ten = ten.trim();
        this.email = email;
        this.role = role == null ? Role.USER : role;
        this.dangHoatDong = true;
    }

    public long getId() { return id; }
    public String getTen() { return ten; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public boolean isDangHoatDong() { return dangHoatDong; }

    public void doiTen(String tenMoi) {
        Validators.khongDuocTrong(tenMoi, "Tên user");
        this.ten = tenMoi.trim();
    }

    public void setEmail(String email) {
        Validators.emailHopLeHoacNull(email);
        this.email = email;
    }

    public void khoa() { this.dangHoatDong = false; }
    public void moKhoa() { this.dangHoatDong = true; }

    @Override
    public String toString() {
        return "User{id=" + id + ", ten='" + ten + "', email='" + email + "', role=" + role + ", dangHoatDong=" + dangHoatDong + "}";
    }
}
