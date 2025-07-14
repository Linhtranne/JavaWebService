package com.data.service.impl;

import com.data.entity.Book;
import com.data.repository.BookRepository;
import com.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}