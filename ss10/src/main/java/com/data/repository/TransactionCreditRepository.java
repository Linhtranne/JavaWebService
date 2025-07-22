package com.data.repository;

import com.data.entity.CreditCard;
import com.data.entity.TransactionCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionCreditRepository extends JpaRepository<TransactionCredit, UUID> {
    List<TransactionCredit> findAllByCreditCardSenderAndCreatedAtBetween(
            CreditCard sender, LocalDateTime start, LocalDateTime end);
}
