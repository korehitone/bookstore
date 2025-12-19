package com.gr_tiga.bookstore.domain.repository;

import com.gr_tiga.bookstore.model.table.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository -- IDK what does this do

// @Repository -- IDK what does do
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    
}
