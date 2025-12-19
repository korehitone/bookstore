package com.gr_tiga.bookstore.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr_tiga.bookstore.model.table.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    
    Optional<Book> findByUid(String uid);
    
    boolean existsByUid(String uid);

    boolean existsByTitleAndAuthorAndPublisher(String title, String author, String publisher);

    void deleteByUid(String uid);
}
