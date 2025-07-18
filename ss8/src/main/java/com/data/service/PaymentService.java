package com.data.service;


import com.data.modal.entity.Payment;
import com.data.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository PaymentRepository;

    public Payment savePayment(Payment Payment) {
        Payment.setCreatedAt(LocalDateTime.now());
        return PaymentRepository.save(Payment);
    }

    public Payment updatePayment(Long id, Payment updatedPayment) {
        Payment existingPayment = PaymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment slip not found with id: " + id));
        existingPayment.setTitle(updatedPayment.getTitle());
        existingPayment.setDescription(updatedPayment.getDescription());
        existingPayment.setMoney(updatedPayment.getMoney());
        return PaymentRepository.save(existingPayment);
    }

    public void deletePayment(Long id) {
        if (!PaymentRepository.existsById(id)) {
            throw new NoSuchElementException("Payment slip not found with id: " + id);
        }
        PaymentRepository.deleteById(id);
    }

    public List<Payment> getAllPayments() {
        return PaymentRepository.findAll();
    }
}