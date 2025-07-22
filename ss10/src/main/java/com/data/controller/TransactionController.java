package com.data.controller;
import com.data.dto.TransactionDTO;
import com.data.service.TransactionService;
import lombok.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDTO dto) {
        try {
            transactionService.processTransaction(dto);
            return new ResponseEntity<>("Giao dịch thành công", HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            log.error("Lỗi dữ liệu giao dịch: {}", ex.getMessage());
            return new ResponseEntity<>("Dữ liệu không hợp lệ: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("Lỗi khi thực hiện giao dịch", ex);
            return new ResponseEntity<>("Đã xảy ra lỗi khi thực hiện giao dịch", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

