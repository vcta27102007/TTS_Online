package com.example.demo;

import com.example.demo.domain.Task;
import com.example.demo.domain.TaskStatus;
import com.example.demo.service.AssignmentService;
import com.example.demo.service.TaskService;
import com.example.demo.util.AppException;
import com.example.demo.util.Log;

import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        AssignmentService assignmentService = new AssignmentService();

        try {
            var p1 = taskService.taoProject("Project thực tập", "Quản lý task đơn giản");

            var u1 = assignmentService.taoUser("An", "an@gmail.com");
            var u2 = assignmentService.taoUser("Binh", "binh@gmail.com");

            Task t1 = taskService.themTask(p1.getId(), "Phân tích nghiệp vụ", "Viết docs tuần 1", LocalDateTime.now().plusDays(3));
            Task t2 = taskService.themTask(p1.getId(), "Code OOP", "Viết class + service", LocalDateTime.now().plusDays(5));

            taskService.doiTrangThai(p1.getId(), t1.getId(), TaskStatus.DANG_LAM);
            taskService.doiMoTa(p1.getId(), t2.getId(), "Viết class domain, service và validate");
            taskService.doiDeadline(p1.getId(), t2.getId(), LocalDateTime.now().plusDays(7));

            assignmentService.ganTaskChoUser(t1, u1.getId());
            assignmentService.ganTaskChoUser(t2, u2.getId());

            Log.thongTin("Task CHUA_LAM: " + taskService.locTaskTheoTrangThai(p1.getId(), TaskStatus.CHUA_LAM));
            Log.thongTin("Task DANG_LAM: " + taskService.locTaskTheoTrangThai(p1.getId(), TaskStatus.DANG_LAM));
            Log.thongTin("Task của user " + u1.getId() + ": " + taskService.locTaskTheoUser(p1.getId(), u1.getId()));
            Log.thongTin("Task của user " + u2.getId() + ": " + taskService.locTaskTheoUser(p1.getId(), u2.getId()));

            taskService.xoaTask(p1.getId(), t2.getId());

            try {
                taskService.themTask(p1.getId(), "   ", null, LocalDateTime.now().plusDays(1));
            } catch (AppException e) {
                Log.loi("Lỗi dự kiến - title rỗng: " + e.getMessage());
            }

            try {
                taskService.xoaTask(p1.getId(), 999);
            } catch (AppException e) {
                Log.loi("Lỗi dự kiến - xoá task không tồn tại: " + e.getMessage());
            }

            try {
                taskService.themTask(p1.getId(), "Task deadline lỗi", "Test validate deadline", LocalDateTime.now().minusDays(1));
            } catch (AppException e) {
                Log.loi("Lỗi dự kiến - deadline quá khứ: " + e.getMessage());
            }

            try {
                assignmentService.ganTaskChoUser(t1, 999);
            } catch (AppException e) {
                Log.loi("Lỗi dự kiến - user không tồn tại: " + e.getMessage());
            }

            Log.thongTin("Hoàn thành demo tuần 1");

        } catch (Exception e) {
            Log.loi("Lỗi không mong muốn: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
