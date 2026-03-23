package com.example.demo.domain;

import com.example.demo.util.Validators;

import java.time.LocalDateTime;

public class Task {
    private final long id;
    private final long projectId;

    private String tieuDe;
    private String moTa;
    private TaskStatus trangThai;
    private LocalDateTime deadline;

    private Long userDuocGiao; // có thể null

    private final LocalDateTime ngayTao;
    private LocalDateTime ngayCapNhat;

    public Task(long id, long projectId, String tieuDe, String moTa, LocalDateTime deadline) {
        Validators.soDuong(id, "Task.id");
        Validators.soDuong(projectId, "Task.projectId");
        Validators.khongDuocTrong(tieuDe, "Tiêu đề task");
        Validators.hanSauHienTaiHoacNull(deadline);

        this.id = id;
        this.projectId = projectId;
        this.tieuDe = tieuDe.trim();
        this.moTa = moTa;
        this.trangThai = TaskStatus.CHUA_LAM;
        this.deadline = deadline;

        this.ngayTao = LocalDateTime.now();
        this.ngayCapNhat = this.ngayTao;
    }

    public long getId() { return id; }
    public long getProjectId() { return projectId; }
    public String getTieuDe() { return tieuDe; }
    public String getMoTa() { return moTa; }
    public TaskStatus getTrangThai() { return trangThai; }
    public LocalDateTime getDeadline() { return deadline; }
    public Long getUserDuocGiao() { return userDuocGiao; }
    public LocalDateTime getNgayTao() { return ngayTao; }
    public LocalDateTime getNgayCapNhat() { return ngayCapNhat; }

    public void doiTieuDe(String tieuDeMoi) {
        Validators.khongDuocTrong(tieuDeMoi, "Tiêu đề task");
        this.tieuDe = tieuDeMoi.trim();
        cham();
    }

    public void doiMoTa(String moTaMoi) {
        this.moTa = moTaMoi;
        cham();
    }

    public void doiTrangThai(TaskStatus trangThaiMoi) {
        if (trangThaiMoi == null) throw new IllegalArgumentException("Trạng thái không được null");
        this.trangThai = trangThaiMoi;
        cham();
    }

    public void doiDeadline(LocalDateTime deadlineMoi) {
        Validators.hanSauHienTaiHoacNull(deadlineMoi);
        this.deadline = deadlineMoi;
        cham();
    }

    public void ganChoUser(Long userId) {
        if (userId != null) Validators.soDuong(userId, "userDuocGiao");
        this.userDuocGiao = userId;
        cham();
    }

    private void cham() {
        this.ngayCapNhat = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Task{id=" + id +
                ", projectId=" + projectId +
                ", tieuDe='" + tieuDe + '\'' +
                ", moTa='" + moTa + '\'' +
                ", trangThai=" + trangThai +
                ", userDuocGiao=" + userDuocGiao +
                ", deadline=" + deadline +
                '}';
    }
}
