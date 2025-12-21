package com.gr_tiga.bookstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr_tiga.bookstore.model.table.BookCart;

public interface BookCartRepository extends JpaRepository<BookCart, Integer> {

    BookCart findByCartIdAndBookId(Integer cartId, Integer BookId);
    
}
