package com.example.demo.util;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public final class Validators {
    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    private Validators() {}

    public static void khongDuocTrong(String value, String tenTruong) {
        if (value == null || value.trim().isEmpty()) {
            throw new AppException(tenTruong + " không được để trống");
        }
    }

    public static void soDuong(long value, String tenTruong) {
        if (value <= 0) throw new AppException(tenTruong + " phải > 0");
    }

    public static void emailHopLeHoacNull(String email) {
        if (email == null) return;
        if (!EMAIL.matcher(email).matches()) throw new AppException("Email không hợp lệ");
    }

    public static void hanSauHienTaiHoacNull(LocalDateTime han) {
        if (han == null) return;
        if (!han.isAfter(LocalDateTime.now())) {
            throw new AppException("Hạn deadline phải sau thời điểm hiện tại");
        }
    }
}
