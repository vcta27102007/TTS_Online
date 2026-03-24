package com.example.demo.task.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class TaskRequest {

    private String tieuDe;
    private String moTa;
    private LocalDateTime deadline;
    private Long userId;
    private Long projectId;

}