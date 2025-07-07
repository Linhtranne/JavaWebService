package com.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
}
