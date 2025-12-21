package com.gr_tiga.bookstore.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr_tiga.bookstore.domain.repository.BookRepository;
import com.gr_tiga.bookstore.domain.repository.BookViewRepository;
import com.gr_tiga.bookstore.helper.exception.DataAlreadyExistsException;
import com.gr_tiga.bookstore.helper.exception.DataNotFoundException;
import com.gr_tiga.bookstore.model.table.Book;
import com.gr_tiga.bookstore.model.view.BookView;

@Service
@Transactional
public class BookService {

    private final BookRepository br;
    private final BookViewRepository bvr;

    public BookService(BookRepository br, BookViewRepository bvr) {
        this.br = br;
        this.bvr = bvr;
    }

    public List<BookView> getAll() {
        return bvr.findAll();
    }

    public Book getByUid(String uid) {
        return br.findByUid(uid)
                .orElseThrow(() -> new DataNotFoundException("Book", uid));
    }

    public List<BookView> getByCategory(String c) {
        return bvr.findByCategoryName(c);
    }

    public List<BookView> getByTitle(String t) {
        return bvr.findByTitleContaining(t);
    }

    public Book insert(Book book) {
        if (br.existsByTitleAndAuthorAndPublisher(book.getTitle(), book.getAuthor(), book.getPublisher())) {

            throw new DataAlreadyExistsException("Book", ("book with title " + book.getTitle() + ", author "
                    + book.getAuthor() + ", publisher " + book.getPublisher() + " already exist"));
        } else {
            return br.save(book);
        }
    }

    public Book update(String uid, Book book) {
        Book oldBook = getByUid(uid);

        oldBook.setCategoryId(book.getCategoryId());
        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setPublisher(book.getPublisher());
        oldBook.setReleaseDate(book.getReleaseDate());
        oldBook.setSipnosis(book.getSipnosis());
        oldBook.setImgUrl(book.getImgUrl());
        oldBook.setPrice(book.getPrice());

        return br.save(oldBook);

    }

    public void delete(String uid) {
        if (!br.existsByUid(uid)) {
            throw new DataNotFoundException("Book", uid);
        } else {
            br.deleteByUid(uid);
        }
    }

    // public List<BookView> getByTitleAndCategory(String query, String category){
    // return bvr.findByTitleAndCategoryName(query, category);
    // }

}
