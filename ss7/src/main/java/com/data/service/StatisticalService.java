package com.data.service;

import com.data.statistical.Statistical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StatisticalService {

    @Autowired
    private Statistical statistical;

    public int countRemainingSeeds() {
        return statistical.countRemainingSeeds();
    }

    public double totalHarvestMoneyThisMonth() {
        return statistical.totalHarvestMoneyThisMonth();
    }

    public double totalPaymentSlipsThisMonth() {
        return statistical.totalPaymentSlipsThisMonth();
    }

    public Map<String, Double> profitLossOverYear() {
        return statistical.profitLossOverYear();
    }

    public double totalWorkerSalaryThisMonth() {
        return statistical.totalWorkerSalaryThisMonth();
    }
}