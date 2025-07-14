package com.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Column(name = "departure", nullable = false)
    private String departure;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "price", nullable = false)
    private double price;

    @OneToMany(mappedBy = "flight")
    private List<Booking> bookings;
}