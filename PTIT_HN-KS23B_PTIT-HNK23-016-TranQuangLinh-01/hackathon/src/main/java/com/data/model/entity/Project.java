package com.data.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int project_id;

    @Column(nullable = false, unique = true, length = 255)
    private String projectName;

    @Column(nullable = false, length = 255)
    private String technology_using;

    @Column(nullable = false)
    private LocalDateTime start_date;

    @Column(nullable = false, columnDefinition = "BIT DEFAULT 1")
    private boolean status;
}