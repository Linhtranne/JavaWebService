package com.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "food_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
}