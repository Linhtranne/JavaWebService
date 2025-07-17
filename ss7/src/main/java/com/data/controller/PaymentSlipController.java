package com.data.controller;

import com.data.entity.PaymentSlip;
import com.data.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentslips")
public class PaymentSlipController {

    @Autowired
    private PaymentSlipService paymentSlipService;

    @GetMapping
    public List<PaymentSlip> getAllPaymentSlips() {
        return paymentSlipService.getAllPaymentSlips();
    }

    @GetMapping("/{id}")
    public PaymentSlip getPaymentSlipById(@PathVariable Long id) {
        return paymentSlipService.getPaymentSlipById(id);
    }

    @PostMapping
    public PaymentSlip addPaymentSlip(@RequestBody PaymentSlip paymentSlip) {
        return paymentSlipService.addPaymentSlip(paymentSlip);
    }
}