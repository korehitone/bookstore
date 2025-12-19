package com.gr_tiga.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gr_tiga.bookstore.domain.service.BookService;
import com.gr_tiga.bookstore.model.table.Book;
import com.gr_tiga.bookstore.model.view.BookView;

import java.util.List;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/book")
public class BookController {
    
    private final BookService bs;

    public BookController(BookService  bs){
        this.bs = bs;
    }

    @GetMapping
    public List<BookView> getBooks(){
        return bs.getAll();
    }

    @GetMapping("/{uid}")
    public Book getBook(@PathVariable String uid) {
        return bs.getByUid(uid);
    }

    @GetMapping("/category")
    public List<BookView> getBooksByCategory(@RequestParam String category){
        return bs.getByCategory(category);
    }

    @GetMapping("/search")
    public List<BookView> search(@RequestParam String title){
        return bs.getByTitle(title);
    }
    // @GetMapping("/search")
    // public List<BookView> searchWithCategory(@RequestParam String title, @RequestParam String category){
    //     return bs.getByTitleAndCategory(title, category);
    // }
    
    
}
