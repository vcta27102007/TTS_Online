package com.example.demo.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer orderId;

    private String method; // COD, MOMO, VNPAY
    private String status; // PENDING, SUCCESS

    private LocalDateTime createdAt;
}
