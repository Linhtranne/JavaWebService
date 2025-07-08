package com.data.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "showtimes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String startTime;
    private String endTime;
    private int numberSeatEmpty;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private ScreenRoom screenRoom;
    @ManyToOne
    private Theater theater;


}
