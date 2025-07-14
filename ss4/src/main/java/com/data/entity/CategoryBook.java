package com.data.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "category_books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cateBookId;

    @Column(name = "cate_book_name", nullable = false)
    private String cateBookName;

    @OneToMany(mappedBy = "categoryBook")
    private List<Book> books;
}