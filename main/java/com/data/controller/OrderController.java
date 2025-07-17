package com.data.controller;
import com.data.repository.*;
import com.data.entity.*;
import com.data.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestParam Long userId,
                                        @RequestBody Map<String, Object> payload) {
        try {
            String fullName = payload.get("fullName").toString();
            String address = payload.get("address").toString();
            String phone = payload.get("phone").toString();
            List<Map<String, Object>> items = (List<Map<String, Object>>) payload.get("items");

            Order order = orderService.placeOrder(userId, items, fullName, address, phone);
            return ResponseEntity.ok(order);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Lỗi khi đặt hàng: " + ex.getMessage());
        }
    }

    /**
     * Lấy danh sách đơn hàng của user
     * GET /orders/user/1
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }

    /**
     * Top 3 người dùng mua nhiều sản phẩm nhất
     * GET /orders/top-users
     */
    @GetMapping("/top-users")
    public ResponseEntity<?> getTopUsers() {
        List<Object[]> topUsers = orderService.getTop3Users();

        // Chuyển kết quả sang dạng dễ đọc
        List<Map<String, Object>> result = topUsers.stream().map(row -> {
            Map<String, Object> m = new HashMap<>();
            m.put("userId", row[0]);
            m.put("fullName", row[1]);
            m.put("totalQuantity", row[2]);
            return m;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}