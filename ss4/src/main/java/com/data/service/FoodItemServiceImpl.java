package com.data.service;
import com.data.entity.FoodItem;
import com.data.repository.FoodItemRepository;
import com.data.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public FoodItem saveFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    @Override
    public FoodItem getFoodItemById(Long id) {
        return foodItemRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }

    @Override
    public Page<FoodItem> getFoodItems(String name, Long categoryId, Pageable pageable) {
        if (name != null && categoryId != null) {
            return foodItemRepository.findByNameContainingAndCategoryId(name, categoryId, pageable);
        } else if (name != null) {
            return foodItemRepository.findByNameContaining(name, pageable);
        } else if (categoryId != null) {
            return foodItemRepository.findByCategoryId(categoryId, pageable);
        } else {
            return foodItemRepository.findAll(pageable);
        }
    }
}