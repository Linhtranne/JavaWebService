package com.data.service;

import com.data.model.entity.Category;
import com.data.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(UUID cateId) {
        return categoryRepository.findById(cateId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(UUID cateId, Category category) {
        Category existing = getCategoryById(cateId);
        existing.setCateName(category.getCateName());
        existing.setStatus(category.isStatus());
        return categoryRepository.save(existing);
    }

    public void deleteCategory(UUID cateId) {
        categoryRepository.deleteById(cateId);
    }
}
