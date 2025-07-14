package com.data.service.impl;

import com.data.entity.CategoryBook;
import com.data.repository.CategoryBookRepository;
import com.data.service.CategoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryBookServiceImpl implements CategoryBookService {

    @Autowired
    private CategoryBookRepository categoryBookRepository;
    @Override
    public List<CategoryBook> getAllCategoryBooks() {
        return categoryBookRepository.findAll();
    }
    @Override
    public CategoryBook getCategoryBookById(Long id) {
        return categoryBookRepository.findById(id).orElse(null);
    }
    @Override
    public CategoryBook saveCategoryBook(CategoryBook categoryBook) {
        return categoryBookRepository.save(categoryBook);
    }
    @Override
    public void deleteCategoryBook(Long id) {
        categoryBookRepository.deleteById(id);
    }
}