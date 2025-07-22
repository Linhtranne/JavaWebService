package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name = "`credit_card`")
@Data
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private Double spendingLimit;
    private Double amountSpent;
    private String status;
    }
