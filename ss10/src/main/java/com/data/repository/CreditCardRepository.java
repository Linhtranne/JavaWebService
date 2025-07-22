package com.data.repository;
import com.data.entity.CreditCard;
import com.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
    Optional<CreditCard> findByAccount(Account account);
    boolean existsByAccount(Account account);
}
