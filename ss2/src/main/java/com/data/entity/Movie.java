package com.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String genre;
    private int duration;
}
