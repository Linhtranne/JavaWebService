package com.data.service;

import com.data.entity.Order;
import com.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByDate(LocalDate date) {
        LocalDate start = LocalDate.from(date.atStartOfDay());
        LocalDateTime end = date.atTime(23, 59, 59);
        return orderRepository.findByCreatedAtBetween(start, LocalDate.from(end));
    }
}