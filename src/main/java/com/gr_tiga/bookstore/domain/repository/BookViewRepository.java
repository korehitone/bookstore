package com.gr_tiga.bookstore.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gr_tiga.bookstore.model.view.BookView;

public interface BookViewRepository extends JpaRepository<BookView, String> {
    
    // @RestResource(path = "byCategoryName")
    // List<BookView> findByCategoryName(String categoryName);

    // @RestResource(path = "byTitle")
    // List<BookView> findByTitleContaining(String title);


    Page<BookView> findByCategoryName(String categoryName, Pageable pageable);

    Page<BookView> findByTitleContaining(String title, Pageable pageable);
    
}
