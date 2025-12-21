package com.gr_tiga.bookstore.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr_tiga.bookstore.model.table.Cart;


public interface CartRepository extends JpaRepository<Cart, Integer> {
    
    Cart findByCustomerId(Integer custId);

    Optional<Cart> findByUid(String uid);

}
