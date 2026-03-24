package com.example.demo.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer customerId;
    private Integer staffId;

    private LocalDateTime orderDate;

    private String status; // MOI, DANG_XU_LY, HOAN_TAT

    private String paymentMethod; // COD, BANKING

    private BigDecimal total;
}
