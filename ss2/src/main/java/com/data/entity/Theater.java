package com.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theaters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
}
