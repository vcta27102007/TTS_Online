package com.example.demo.task.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TaskRequest {

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(max = 255, message = "Tiêu đề không được vượt quá 255 ký tự")
    private String tieuDe;

    @NotBlank(message = "Mô tả không được để trống")
    @Size(max = 1000, message = "Mô tả không được vượt quá 1000 ký tự")
    private String moTa;

    @NotNull(message = "Deadline không được để trống")
    @Future(message = "Deadline phải lớn hơn thời điểm hiện tại")
    private LocalDateTime deadline;

    @NotNull(message = "userId không được để trống")
    private Long userId;

    @NotNull(message = "projectId không được để trống")
    private Long projectId;
}