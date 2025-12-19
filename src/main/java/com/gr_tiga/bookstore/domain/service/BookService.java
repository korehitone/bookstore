package com.gr_tiga.bookstore.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr_tiga.bookstore.domain.repository.BookRepository;
import com.gr_tiga.bookstore.domain.repository.BookViewRepository;
import com.gr_tiga.bookstore.model.table.Book;
import com.gr_tiga.bookstore.model.view.BookView;

@Service
@Transactional
public class BookService {
    
    private final BookRepository br;
    private final BookViewRepository bvr;

    public BookService(BookRepository br, BookViewRepository bvr){
        this.br = br;
        this.bvr = bvr;
    }

    public List<BookView> getAll(){
        return bvr.findAll();
    }

    public Book getByUid(String uid){
        return br.findByUid(uid);
    }

    public List<BookView> getByCategory(String c){
        return bvr.findByCategoryName(c);
    }

    public List<BookView> getByTitle(String t){
        return bvr.findByTitle(t);
    }

    // public List<BookView> getByTitleAndCategory(String query, String category){
    //     return bvr.findByTitleAndCategoryName(query, category);
    // }
    
}
