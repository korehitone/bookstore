package com.gr_tiga.bookstore.model.table;

import jakarta.persistence.*;

@Entity
@Table(name = "category_book")
public class CategoryBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;
}
