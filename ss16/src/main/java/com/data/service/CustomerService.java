package com.data.service;

import com.data.model.entity.Customer;
import com.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(Customer customer) {
        if (customerRepository.findByUsername(customer.getUsername()).isPresent()) {
            return "Tên đăng nhập đã tồn tại";
        }
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            return "Email đã tồn tại";
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setStatus(true);
        customer.setIsLogin(false);
        customerRepository.save(customer);
        return "đăng kí thành công";
    }

    public String login(String username, String password) {
        Optional<Customer> customerOpt = customerRepository.findByUsername(username);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (passwordEncoder.matches(password, customer.getPassword())) {
                customer.setIsLogin(true);
                customerRepository.save(customer);
                return "Đăng nhập thành công";
            }
        }
        return "Sai tên tài khoản hoặc mật khẩu";
    }

    public String logout(String username) {
        Optional<Customer> customerOpt = customerRepository.findByUsername(username);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setIsLogin(false);
            customerRepository.save(customer);
            return "Đăng xuất thành công";
        }
        return "Không tìm thấy người dùng";
    }

    public boolean checkDuplicate(String username, String email) {
        return customerRepository.findByUsername(username).isPresent() ||
                customerRepository.findByEmail(email).isPresent();
    }
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));
    }
}
