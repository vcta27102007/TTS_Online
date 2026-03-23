package com.example.demo.domain;

import com.example.demo.util.AppException;
import com.example.demo.util.Validators;

import java.util.*;

public class Project {
    private final long id;
    private String tenProject;
    private String moTa;

    private final Map<Long, Task> dsTask = new LinkedHashMap<>();

    public Project(long id, String tenProject, String moTa) {
        Validators.soDuong(id, "Project.id");
        Validators.khongDuocTrong(tenProject, "Tên project");
        this.id = id;
        this.tenProject = tenProject.trim();
        this.moTa = moTa;
    }

    public long getId() { return id; }
    public String getTenProject() { return tenProject; }
    public String getMoTa() { return moTa; }

    public Collection<Task> getDsTask() {
        return Collections.unmodifiableCollection(dsTask.values());
    }

    public Optional<Task> timTask(long taskId) {
        return Optional.ofNullable(dsTask.get(taskId));
    }

    public void themTask(Task task) {
        if (task == null) {
            throw new AppException("Task không được null");
        }
        if (dsTask.containsKey(task.getId())) {
            throw new AppException("Task bị trùng id: " + task.getId());
        }
        dsTask.put(task.getId(), task);
    }

    public void xoaTask(long taskId) {
        dsTask.remove(taskId);
    }

    public boolean tonTaiTask(long taskId) {
        return dsTask.containsKey(taskId);
    }

    @Override
    public String toString() {
        return "Project{id=" + id + ", tenProject='" + tenProject + "', soTask=" + dsTask.size() + "}";
    }
}
