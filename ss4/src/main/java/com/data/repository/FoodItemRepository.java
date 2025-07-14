package com.data.repository;
import com.data.entity.FoodItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    Page<FoodItem> findByNameContainingAndCategoryId(String name, Long categoryId, Pageable pageable);
    Page<FoodItem> findByNameContaining(String name, Pageable pageable);
    Page<FoodItem> findByCategoryId(Long categoryId, Pageable pageable);
}