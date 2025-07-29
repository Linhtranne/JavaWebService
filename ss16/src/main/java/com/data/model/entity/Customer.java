package com.data.model.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String fullName;

    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private Boolean isLogin = false;

    private Boolean status = true;
}