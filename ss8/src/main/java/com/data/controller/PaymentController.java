package com.data.controller;


import com.data.modal.entity.Payment;
import com.data.response.ApiResponse;
import com.data.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Payments")
public class PaymentController {

    @Autowired
    private PaymentService PaymentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Payment>> createPayment(@RequestBody Payment Payment) {
        Payment savedPayment = PaymentService.savePayment(Payment);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment slip created successfully", savedPayment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Payment>> updatePayment(@PathVariable Long id, @RequestBody Payment Payment) {
        Payment updatedPayment = PaymentService.updatePayment(id, Payment);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment slip updated successfully", updatedPayment));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Payment>>> getAllPayments() {
        List<Payment> Payments = PaymentService.getAllPayments();
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment slips retrieved successfully", Payments));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePayment(@PathVariable Long id) {
        PaymentService.deletePayment(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment slip deleted successfully", null));
    }
}