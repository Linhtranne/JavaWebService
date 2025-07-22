package com.data.service;
import com.data.entity.CreditCard;
import com.data.entity.Account;
import com.data.repository.CreditCardRepository;
import com.data.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public CreditCard createCreditCard(UUID accountId, CreditCard card) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tài khoản"));

        if (creditCardRepository.existsByAccount(account)) {
            throw new IllegalStateException("Tài khoản đã có thẻ tín dụng");
        }

        card.setId(null);
        card.setAccount(account);
        card.setAmountSpent(0.0);
        card.setStatus("active");

        return creditCardRepository.save(card);
    }

    public CreditCard updateLimit(UUID cardId, Double newLimit) {
        CreditCard card = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thẻ"));

        card.setSpendingLimit(newLimit);
        return creditCardRepository.save(card);
    }

    public CreditCard updateStatus(UUID cardId, String status) {
        CreditCard card = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy thẻ"));

        card.setStatus(status);
        return creditCardRepository.save(card);
    }
}
