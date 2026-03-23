package com.example.demo.service;

import com.example.demo.domain.Task;
import com.example.demo.domain.User;
import com.example.demo.util.AppException;
import com.example.demo.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

public class AssignmentService {
    private final Map<Long, User> dsUser = new LinkedHashMap<>();
    private final IdGenerator genUserId = new IdGenerator(1);

    public User taoUser(String ten, String email) {
        long id = genUserId.nextId();
        User u = new User(id, ten, email, null);
        dsUser.put(id, u);
        Log.thongTin("Tạo user: " + u);
        return u;
    }

    public User layUser(long userId) {
        User u = dsUser.get(userId);
        if (u == null) throw new AppException("Không tìm thấy user: " + userId);
        return u;
    }

    public void ganTaskChoUser(Task task, long userId) {
        User u = layUser(userId);
        if (!u.isDangHoatDong()) throw new AppException("User đang bị khoá: " + userId);

        task.ganChoUser(userId);
        Log.thongTin("Gán taskId=" + task.getId() + " cho userId=" + userId);
    }
}
