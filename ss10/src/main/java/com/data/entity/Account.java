package com.data.entity;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
@Table(name = "`account`")
@Data
@Entity
public class Account {
    @Id
    @GeneratedValue
    private UUID id;
    private String fullName;
    private String phone;
    private String cccd;
    private String email;
    private Double money;
    @Enumerated(EnumType.STRING)
    public Status status;
}
