package com.data.repository;

import com.data.entity.OrderDetail;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT od.order.user.userId, od.order.user.fullName, SUM(od.quantity) as total " +
            "FROM OrderDetail od GROUP BY od.order.user.userId, od.order.user.fullName " +
            "ORDER BY total DESC")
    List<Object[]> findTopUsers(Pageable pageable);

}