package com.data.controller;

import com.data.dto.TransactionCreditDTO;
import com.data.entity.TransactionCredit;
import com.data.service.TransactionCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-transactions")
@RequiredArgsConstructor
public class TransactionCreditController {

    private final TransactionCreditService transactionCreditService;

    @PostMapping
    public ResponseEntity<TransactionCredit> createTransaction(@RequestBody TransactionCreditDTO dto) {
        TransactionCredit result = transactionCreditService.processCreditTransaction(dto);
        return ResponseEntity.status(201).body(result);
    }
}
