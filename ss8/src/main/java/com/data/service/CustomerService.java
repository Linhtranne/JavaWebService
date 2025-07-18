package com.data.service;

import com.data.modal.entity.Customer;
import com.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        customer.setCreatedAt(LocalDateTime.now());
        customer.setStatus(true); // Default status as active
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with id: " + id));
        existingCustomer.setFullName(updatedCustomer.getFullName());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setNumberOfPayments(updatedCustomer.getNumberOfPayments());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with id: " + id));
        existingCustomer.setStatus(false); // Soft delete by setting status to false
        customerRepository.save(existingCustomer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}