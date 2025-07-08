package com.data.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String seatNumber;
    @ManyToOne
    private ScreenRoom screenRoom;
}
