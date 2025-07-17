package com.data.service;

import com.data.entity.PaymentSlip;
import com.data.repository.PaymentSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentSlipService {

    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    public List<PaymentSlip> getAllPaymentSlips() {
        return paymentSlipRepository.findAll();
    }

    public PaymentSlip getPaymentSlipById(Long id) {
        return paymentSlipRepository.findById(id).orElseThrow(() -> new RuntimeException("PaymentSlip not found"));
    }

    public PaymentSlip addPaymentSlip(PaymentSlip paymentSlip) {
        return paymentSlipRepository.save(paymentSlip);
    }
}