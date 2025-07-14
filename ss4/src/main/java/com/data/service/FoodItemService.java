package com.data.service;
import com.data.entity.FoodItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodItemService {
    FoodItem saveFoodItem(FoodItem foodItem);
    FoodItem getFoodItemById(Long id);
    void deleteFoodItem(Long id);
    Page<FoodItem> getFoodItems(String name, Long categoryId, Pageable pageable);
}