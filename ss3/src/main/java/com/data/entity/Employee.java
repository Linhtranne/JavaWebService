package com.data.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "phone_number", nullable = false, unique = true, length = 15)
    private String phoneNumber;
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;
    @Column(name = "salary", nullable = false)
    private double salary;
    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
