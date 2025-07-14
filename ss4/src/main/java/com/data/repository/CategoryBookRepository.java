package com.data.repository;

import com.data.entity.CategoryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryBookRepository extends JpaRepository<CategoryBook, Long> {
    List<CategoryBook> getCategoryBookByCateBookNameContains(String cateBookName);
}