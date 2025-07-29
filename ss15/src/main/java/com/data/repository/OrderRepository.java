package com.data.repository;

import com.data.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserUserId(UUID userId);
    List<Order> findByStatus(Order.OrderStatus status);

    List<Order> findByUserUserId(Long userId);
}