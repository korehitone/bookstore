package com.gr_tiga.bookstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr_tiga.bookstore.model.view.CartView;

public interface CartViewRepository extends JpaRepository<CartView, Integer> {
    
    CartView findByCustomerId(Integer custId);

}
