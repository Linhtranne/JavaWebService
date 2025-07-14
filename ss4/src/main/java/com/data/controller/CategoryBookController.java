package com.data.controller;

import com.data.entity.CategoryBook;
import com.data.service.CategoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category-books")
public class CategoryBookController {

    @Autowired
    private CategoryBookService categoryBookService;

    @GetMapping
    public String getAllCategoryBooks(Model model) {
        model.addAttribute("categoryBooks", categoryBookService.getAllCategoryBooks());
        return "category-books";
    }

    @GetMapping("/{id}")
    public String getCategoryBookById(@PathVariable Long id, Model model) {
        CategoryBook categoryBook = categoryBookService.getCategoryBookById(id);
        model.addAttribute("categoryBook", categoryBook);
        return "category-book-details";
    }

    @GetMapping("/new")
    public String newCategoryBook(Model model) {
        model.addAttribute("categoryBook", new CategoryBook());
        return "category-book-form";
    }

    @PostMapping
    public String saveCategoryBook(@ModelAttribute CategoryBook categoryBook) {
        categoryBookService.saveCategoryBook(categoryBook);
        return "redirect:/category-books";
    }

    @GetMapping("/{id}/edit")
    public String editCategoryBook(@PathVariable Long id, Model model) {
        CategoryBook categoryBook = categoryBookService.getCategoryBookById(id);
        model.addAttribute("categoryBook", categoryBook);
        return "category-book-form";
    }

    @GetMapping("/{id}/delete")
    public String deleteCategoryBook(@PathVariable Long id) {
        categoryBookService.deleteCategoryBook(id);
        return "redirect:/category-books";
    }
}