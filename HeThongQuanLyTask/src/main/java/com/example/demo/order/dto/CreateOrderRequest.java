package com.example.demo.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
    private Integer customerId;
    private String paymentMethod;
}
