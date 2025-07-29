package com.data.model.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private String producer;
    private int yearMaking;
    private String expireDate;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;
}