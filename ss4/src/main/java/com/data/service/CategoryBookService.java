package com.data.service;

import com.data.entity.CategoryBook;
import java.util.List;

public interface CategoryBookService {
    List<CategoryBook> getAllCategoryBooks();
    CategoryBook getCategoryBookById(Long id);
    CategoryBook saveCategoryBook(CategoryBook categoryBook);
    void deleteCategoryBook(Long id);
}