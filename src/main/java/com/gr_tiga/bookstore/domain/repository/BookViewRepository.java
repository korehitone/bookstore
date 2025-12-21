package com.gr_tiga.bookstore.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr_tiga.bookstore.model.view.BookView;

public interface BookViewRepository extends JpaRepository<BookView, String> {
    
    List<BookView> findByCategoryName(String categoryName);
    List<BookView> findByTitleContaining(String title);
    // List<BookView> findByTitleAndCategoryName(String title, String categoryName);
}
