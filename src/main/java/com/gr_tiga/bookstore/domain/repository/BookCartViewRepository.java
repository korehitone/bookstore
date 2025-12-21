package com.gr_tiga.bookstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr_tiga.bookstore.model.view.BookCartView;
import java.util.List;


public interface BookCartViewRepository extends JpaRepository<BookCartView, Integer> {
    
    List<BookCartView> findByCartId(Integer cartId);
}
