package com.gr_tiga.bookstore.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gr_tiga.bookstore.model.view.BookCartView;


public interface BookCartViewRepository extends JpaRepository<BookCartView, Integer> {
    
    Page<BookCartView> findByCartId(Integer cartId, Pageable pageable);
}
