package com.gr_tiga.bookstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gr_tiga.bookstore.model.view.BookView;

public interface BookCartViewRepository extends JpaRepository<BookView, String> {
    
}
