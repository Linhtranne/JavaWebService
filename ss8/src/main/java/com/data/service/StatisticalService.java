package com.data.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
public class StatisticalService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> getTopDishes() {
        String jpql = "SELECT new map(d.id as id, d.name as name, SUM(od.quantity) as totalQuantity) " +
                "FROM OrderDetail od JOIN od.dish d " +
                "GROUP BY d.id, d.name " +
                "ORDER BY SUM(od.quantity) DESC";
        Query query = entityManager.createQuery(jpql);
        query.setMaxResults(10);
        return query.getResultList();
    }

    public List<Map<String, Object>> getTopCustomers() {
        String jpql = "SELECT new map(c.id as id, c.fullName as name, SUM(o.totalMoney) as totalSpent) " +
                "FROM Order o JOIN o.customer c " +
                "GROUP BY c.id, c.fullName " +
                "ORDER BY SUM(o.totalMoney) DESC";
        Query query = entityManager.createQuery(jpql);
        query.setMaxResults(10);
        return query.getResultList();
    }
    public Double getCurrentMonthExpenses() {
        String jpql = "SELECT SUM(od.priceBuy * od.quantity) " +
                "FROM OrderDetail od " +
                "WHERE FUNCTION('MONTH', od.order.createdAt) = FUNCTION('MONTH', CURRENT_DATE) " +
                "AND FUNCTION('YEAR', od.order.createdAt) = FUNCTION('YEAR', CURRENT_DATE)";
        Double result = (Double) entityManager.createQuery(jpql).getSingleResult();
        return result != null ? result : 0.0;
    }

    public Map<String, Double> getMonthlyExpenses() {
        String jpql = "SELECT FUNCTION('MONTH', od.order.createdAt), SUM(od.priceBuy * od.quantity) " +
                "FROM OrderDetail od " +
                "GROUP BY FUNCTION('MONTH', od.order.createdAt)";
        List<Object[]> results = entityManager.createQuery(jpql).getResultList();
        Map<String, Double> map = new HashMap<>();
        for (Object[] row : results) {
            map.put("Tháng " + row[0], (Double) row[1]);
        }
        return map;
    }

    public Map<String, Double> getMonthlyRevenue() {
        String jpql = "SELECT FUNCTION('MONTH', o.createdAt), SUM(o.totalMoney) " +
                "FROM Order o " +
                "GROUP BY FUNCTION('MONTH', o.createdAt)";
        List<Object[]> results = entityManager.createQuery(jpql).getResultList();
        Map<String, Double> map = new HashMap<>();
        for (Object[] row : results) {
            map.put("Tháng " + row[0], (Double) row[1]);
        }
        return map;
    }

    public Map<String, Object> getTopEmployee() {
        String jpql = "SELECT new map(e.id as id, e.fullname as name, SUM(o.totalMoney) as totalRevenue) " +
                "FROM Order o JOIN o.employee e " +
                "WHERE FUNCTION('MONTH', o.createdAt) = FUNCTION('MONTH', CURRENT_DATE) " +
                "AND FUNCTION('YEAR', o.createdAt) = FUNCTION('YEAR', CURRENT_DATE) " +
                "GROUP BY e.id, e.fullname " +
                "ORDER BY SUM(o.totalMoney) DESC";
        Query query = entityManager.createQuery(jpql);
        query.setMaxResults(1);
        List<Map<String, Object>> result = query.getResultList();
        return result.isEmpty() ? new HashMap<>() : result.get(0);
    }
}