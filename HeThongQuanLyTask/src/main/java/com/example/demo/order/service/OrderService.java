package com.example.demo.order.service;

import com.example.demo.order.dto.CreateOrderRequest;
import com.example.demo.order.dto.OrderResponse;
import com.example.demo.order.entity.OrderEntity;
import com.example.demo.order.repository.OrderItemRepository;
import com.example.demo.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;
    private final OrderItemRepository itemRepo;

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest req) {
        OrderEntity order = new OrderEntity();
        order.setCustomerId(req.getCustomerId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("MOI");
        order.setPaymentMethod(req.getPaymentMethod());
        order.setTotal(BigDecimal.ZERO);

        return mapToResponse(orderRepo.save(order));
    }

    public List<OrderResponse> getAll() {
        return orderRepo.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    private OrderResponse mapToResponse(OrderEntity order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setStatus(order.getStatus());
        response.setTotal(order.getTotal());
        return response;
    }
}
