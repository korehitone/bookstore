package com.gr_tiga.bookstore.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr_tiga.bookstore.model.table.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByUid(String uid);

    Customer findByEmail(String email);

    boolean existsByUid(String uid);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void deleteByUid(String uid);
}
