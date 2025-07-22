package com.data.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "transaction_credit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Account accountReceiver;

    @ManyToOne
    @JoinColumn(name = "credit_card_sender_id")
    private CreditCard creditCardSender;

    private String note;
    private Double money;
    private String status;
}
