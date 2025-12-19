package com.gr_tiga.bookstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr_tiga.bookstore.model.table.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
}
