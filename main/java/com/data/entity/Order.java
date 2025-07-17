package com.data.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data

@Table(name = "`order`")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDateTime orderDate;
    private String fullName;
    private String address;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}