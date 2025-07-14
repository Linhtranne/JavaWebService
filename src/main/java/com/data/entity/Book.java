package com.data.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "year_publish", nullable = false)
    private int yearPublish;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_book_id", nullable = false)
    private CategoryBook categoryBook;
}