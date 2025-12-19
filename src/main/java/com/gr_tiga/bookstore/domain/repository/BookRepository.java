package com.gr_tiga.bookstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr_tiga.bookstore.model.table.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    
    Book findByUid(String uid);
}
