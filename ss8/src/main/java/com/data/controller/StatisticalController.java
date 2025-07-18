package com.data.controller;

import com.data.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticalController {

    @Autowired
    private StatisticalService statisticalService;

    @GetMapping("/top-dishes")
    public ResponseEntity<List<Map<String, Object>>> getTopDishes() {
        List<Map<String, Object>> topDishes = statisticalService.getTopDishes();
        return ResponseEntity.ok(topDishes);
    }

    @GetMapping("/top-customers")
    public ResponseEntity<List<Map<String, Object>>> getTopCustomers() {
        List<Map<String, Object>> topCustomers = statisticalService.getTopCustomers();
        return ResponseEntity.ok(topCustomers);
    }

    @GetMapping("/current-month-expenses")
    public ResponseEntity<Double> getCurrentMonthExpenses() {
        Double currentMonthExpenses = statisticalService.getCurrentMonthExpenses();
        return ResponseEntity.ok(currentMonthExpenses);
    }

    @GetMapping("/monthly-expenses")
    public ResponseEntity<Map<String, Double>> getMonthlyExpenses() {
        Map<String, Double> monthlyExpenses = statisticalService.getMonthlyExpenses();
        return ResponseEntity.ok(monthlyExpenses);
    }

    @GetMapping("/monthly-revenue")
    public ResponseEntity<Map<String, Double>> getMonthlyRevenue() {
        Map<String, Double> monthlyRevenue = statisticalService.getMonthlyRevenue();
        return ResponseEntity.ok(monthlyRevenue);
    }

    @GetMapping("/top-employee")
    public ResponseEntity<Map<String, Object>> getTopEmployee() {
        Map<String, Object> topEmployee = statisticalService.getTopEmployee();
        return ResponseEntity.ok(topEmployee);
    }
}