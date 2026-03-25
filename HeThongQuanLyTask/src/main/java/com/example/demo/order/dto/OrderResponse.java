package com.example.demo.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderResponse {
    private Integer id;
    private BigDecimal total;
    private String status;
}