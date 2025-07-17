package com.data.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private String producer;

    // Quan hệ 1-n với ProductDetail
    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetails;
}