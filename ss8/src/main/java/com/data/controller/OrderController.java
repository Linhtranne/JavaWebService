package com.data.controller;

import com.data.modal.entity.Order;
import com.data.modal.entity.OrderRequest;
import com.data.response.ApiResponse;
import com.data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody OrderRequest orderRequest) {
        Order savedOrder = orderService.saveOrder(orderRequest.getOrder(), orderRequest.getOrderDetails());
        return ResponseEntity.ok(new ApiResponse<>(true, "Order created successfully", savedOrder));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(new ApiResponse<>(true, "Orders retrieved successfully", orders));
    }
}