package com.data.service;

import com.data.entity.*;
import com.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired private UserRepository userRepo;
    @Autowired private OrderRepository orderRepo;
    @Autowired
    private OrderDetailRepository orderDetailRepo;
    @Autowired private ProductDetailRepository productDetailRepo;

    public Order placeOrder(Long userId, List<Map<String, Object>> items, String fullName, String address, String phone) {
        User user = userRepo.findById(userId).orElseThrow();

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setFullName(fullName);
        order.setAddress(address);
        order.setPhone(phone);
        order = orderRepo.save(order);

        for (Map<String, Object> item : items) {
            Long productDetailId = Long.valueOf(item.get("productDetailId").toString());
            Integer quantity = Integer.valueOf(item.get("quantity").toString());

            ProductDetail pd = productDetailRepo.findById(productDetailId).orElseThrow();

            OrderDetail od = new OrderDetail();
            od.setOrder(order);
            od.setProductDetail(pd);
            od.setQuantity(quantity);
            od.setPrice(quantity * pd.getPrice());
            orderDetailRepo.save(od);
        }

        return order;
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepo.findAll().stream()
                .filter(o -> o.getUser().getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Object[]> getTop3Users() {
        return orderDetailRepo.findTopUsers(PageRequest.of(0, 3));
    }
}