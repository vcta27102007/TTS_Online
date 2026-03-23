package com.example.demo.util;

import java.time.LocalDateTime;

public final class Log {
    private Log() {}

    public static void thongTin(String msg) {
        System.out.println(LocalDateTime.now() + " [THONG_TIN] " + msg);
    }

    public static void canhBao(String msg) {
        System.out.println(LocalDateTime.now() + " [CANH_BAO] " + msg);
    }

    public static void loi(String msg) {
        System.out.println(LocalDateTime.now() + " [LOI] " + msg);
    }
}
