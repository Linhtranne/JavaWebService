package com.data.service;

import com.data.model.entity.*;
import com.data.repository.OrderDetailRepository;
import com.data.repository.OrderRepository;
import com.data.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartService cartService;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository,
                        CartService cartService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void updateOrderStatus(UUID orderId, Order.OrderStatus status) {
        Order order = getOrderById(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Transactional
    public void placeOrder(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartService.getCartByUser(username);

        Order order = new Order();
        order.setUser(user);
        order.setFullName(user.getFullName());
        order.setAddress(user.getAddress());
        order.setEmail(user.getEmail());
        order.setPhone(user.getPhone());
        order.setStatus(Order.OrderStatus.PENDING);
        orderRepository.save(order);

        for (CartItem item : cart.getCartItems()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            detail.setPrice(item.getProduct().getPrice());
            orderDetailRepository.save(detail);
        }

        cartService.clearCart(username);
    }

    public List<Order> getOrderHistory(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUserUserId(user.getUserId());
    }

    public Order getOrderDetails(String username, UUID orderId) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Order order = getOrderById(orderId);
        if (!order.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized access to order");
        }
        return order;
    }

    public List<Order> getOrderHistoryByStatus(String username, Order.OrderStatus status) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUserUserId(user.getUserId()).stream()
                .filter(order -> order.getStatus() == status)
                .toList();
    }
}