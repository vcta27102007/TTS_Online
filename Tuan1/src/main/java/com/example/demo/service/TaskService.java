package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.domain.Task;
import com.example.demo.domain.TaskStatus;
import com.example.demo.util.AppException;
import com.example.demo.util.Log;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskService {
    private final Map<Long, Project> dsProject = new LinkedHashMap<>();
    private final IdGenerator genProjectId = new IdGenerator(1);
    private final IdGenerator genTaskId = new IdGenerator(1);

    public Project taoProject(String ten, String moTa) {
        long id = genProjectId.nextId();
        Project p = new Project(id, ten, moTa);
        dsProject.put(id, p);
        Log.thongTin("Tạo project: " + p);
        return p;
    }

    public Project layProject(long projectId) {
        Project p = dsProject.get(projectId);
        if (p == null) throw new AppException("Không tìm thấy project: " + projectId);
        return p;
    }

    public Task themTask(long projectId, String tieuDe, String moTa, LocalDateTime deadline) {
        Project p = layProject(projectId);

        long id = genTaskId.nextId();
        Task task = new Task(id, projectId, tieuDe, moTa, deadline);
        p.themTask(task);
        Log.thongTin("Thêm task: " + task);
        return task;
    }

    public Task doiTieuDe(long projectId, long taskId, String tieuDeMoi) {
        Task t = timTask(projectId, taskId);
        t.doiTieuDe(tieuDeMoi);
        Log.thongTin("Cập nhật tiêu đề task: " + t);
        return t;
    }

    public Task doiMoTa(long projectId, long taskId, String moTaMoi) {
        Task t = timTask(projectId, taskId);
        t.doiMoTa(moTaMoi);
        Log.thongTin("Cập nhật mô tả task: " + t);
        return t;
    }

    public Task doiTrangThai(long projectId, long taskId, TaskStatus status) {
        Task t = timTask(projectId, taskId);
        t.doiTrangThai(status);
        Log.thongTin("Cập nhật trạng thái task: " + t);
        return t;
    }

    public Task doiDeadline(long projectId, long taskId, LocalDateTime deadlineMoi) {
        Task t = timTask(projectId, taskId);
        t.doiDeadline(deadlineMoi);
        Log.thongTin("Cập nhật deadline task: " + t);
        return t;
    }

    public void xoaTask(long projectId, long taskId) {
        Project p = layProject(projectId);
        if (!p.tonTaiTask(taskId)) throw new AppException("Không tìm thấy task: " + taskId);
        p.xoaTask(taskId);
        Log.thongTin("Xoá taskId=" + taskId + " khỏi projectId=" + projectId);
    }

    public Task timTask(long projectId, long taskId) {
        Project p = layProject(projectId);
        return p.timTask(taskId).orElseThrow(() -> new AppException("Không tìm thấy task: " + taskId));
    }

    public List<Task> locTaskTheoTrangThai(long projectId, TaskStatus status) {
        Project p = layProject(projectId);
        return p.getDsTask().stream()
                .filter(t -> t.getTrangThai() == status)
                .collect(Collectors.toList());
    }

    public List<Task> locTaskTheoUser(long projectId, long userId) {
        Project p = layProject(projectId);
        return p.getDsTask().stream()
                .filter(t -> t.getUserDuocGiao() != null && t.getUserDuocGiao().equals(userId))
                .collect(Collectors.toList());
    }
}
