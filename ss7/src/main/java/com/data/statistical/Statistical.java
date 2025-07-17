package com.data.statistical;

import com.data.entity.Harvest;
import com.data.entity.PaymentSlip;
import com.data.entity.Seed;
import com.data.entity.Worker;
import com.data.repository.HarvestRepository;
import com.data.repository.PaymentSlipRepository;
import com.data.repository.SeedRepository;
import com.data.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Statistical {

    @Autowired
    private SeedRepository seedRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    @Autowired
    private WorkerRepository workerRepository;

    public int countRemainingSeeds() {
        return seedRepository.findAll().stream().mapToInt(Seed::getQuantity).sum();
    }

    public double totalHarvestMoneyThisMonth() {
        YearMonth currentMonth = YearMonth.now();
        return harvestRepository.findAll().stream()
                .filter(harvest -> YearMonth.from(harvest.getCreatedAt()).equals(currentMonth))
                .mapToDouble(Harvest::getTotalMoney)
                .sum();
    }

    public double totalPaymentSlipsThisMonth() {
        YearMonth currentMonth = YearMonth.now();
        return paymentSlipRepository.findAll().stream()
                .filter(paymentSlip -> YearMonth.from(paymentSlip.getCreatedAt()).equals(currentMonth))
                .mapToDouble(PaymentSlip::getMoney)
                .sum();
    }

    public Map<String, Double> profitLossOverYear() {
        Map<String, Double> profitLoss = new HashMap<>();
        YearMonth currentYear = YearMonth.now();
        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(currentYear.getYear(), month);
            double totalHarvest = harvestRepository.findAll().stream()
                    .filter(harvest -> YearMonth.from(harvest.getCreatedAt()).equals(yearMonth))
                    .mapToDouble(Harvest::getTotalMoney)
                    .sum();
            double totalPayment = paymentSlipRepository.findAll().stream()
                    .filter(paymentSlip -> YearMonth.from(paymentSlip.getCreatedAt()).equals(yearMonth))
                    .mapToDouble(PaymentSlip::getMoney)
                    .sum();
            profitLoss.put(yearMonth.toString(), totalHarvest - totalPayment);
        }
        return profitLoss;
    }

    public double totalWorkerSalaryThisMonth() {
        YearMonth currentMonth = YearMonth.now();
        return workerRepository.findAll().stream()
                .mapToDouble(Worker::getSalary)
                .sum();
    }
}