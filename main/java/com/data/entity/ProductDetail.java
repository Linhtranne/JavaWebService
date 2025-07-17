package com.data.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "`product_detail`")
public class ProductDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productDetailId;

    private Integer yearMaking;
    private String color;
    private String size;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}