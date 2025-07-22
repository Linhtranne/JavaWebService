package com.data.controller;

import com.data.entity.CreditCard;
import com.data.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/creditcards")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<CreditCard> createCard(
            @RequestParam UUID accountId,
            @RequestBody CreditCard card
    ) {
        CreditCard created = creditCardService.createCreditCard(accountId, card);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateLimit(
            @PathVariable UUID id,
            @RequestParam Double newLimit
    ) {
        CreditCard updated = creditCardService.updateLimit(id, newLimit);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CreditCard> updateStatus(
            @PathVariable UUID id,
            @RequestParam String status
    ) {
        CreditCard updated = creditCardService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}
