package com.data.service;

import com.data.modal.entity.Order;
import com.data.modal.entity.OrderDetail;
import com.data.repository.OrderDetailRepository;
import com.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    public Order saveOrder(Order order, List<OrderDetail> orderDetails) {
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        for (OrderDetail detail : orderDetails) {
            detail.setOrder(savedOrder);
            orderDetailRepository.save(detail);
        }

        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}