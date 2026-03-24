package com.example.demo.order.controller;

import com.example.demo.common.response.ApiResponse;
import com.example.demo.order.dto.CreateOrderRequest;
import com.example.demo.order.dto.OrderResponse;
import com.example.demo.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> create(@RequestBody CreateOrderRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Tạo order thành công", orderService.createOrder(req)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách order thành công", orderService.getAll()));
    }

    @PostMapping("/{orderId}/pay")
    public ResponseEntity<ApiResponse<String>> pay(@PathVariable Integer orderId) {
        return ResponseEntity.ok(ApiResponse.success("Thanh toán thành công", "Thanh toán thành công (fake), orderId=" + orderId));
    }
}
