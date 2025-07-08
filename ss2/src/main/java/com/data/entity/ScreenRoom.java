package com.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "screenrooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScreenRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int capacity;
    @ManyToOne
    private Theater theater;


}
