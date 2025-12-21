package com.gr_tiga.bookstore.domain.repository;

import java.util.Optional;

import com.gr_tiga.bookstore.model.table.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository -- IDK what does this do

// @Repository -- IDK what does do
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findById(Integer id);  // Already available from JpaRepository, but explicit

    Admin findByEmail(String email);  // Returns Admin directly (matching CustomerRepository style)

    boolean existsById(Integer id);  // Already available from JpaRepository, but explicit

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void deleteById(Integer id);  // Already available from JpaRepository, but explicit
}
