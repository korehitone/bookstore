package com.gr_tiga.bookstore.domain.repository;

import com.gr_tiga.bookstore.model.table.Category;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository; IDK what does this do

// @Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
