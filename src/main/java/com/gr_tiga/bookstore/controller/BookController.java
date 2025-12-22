package com.gr_tiga.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gr_tiga.bookstore.domain.service.BookService;
import com.gr_tiga.bookstore.helper.ErrorResponse;
import com.gr_tiga.bookstore.model.table.Book;
import com.gr_tiga.bookstore.model.view.BookView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bs;

    public BookController(BookService bs) {
        this.bs = bs;
    }

    @GetMapping
    @Operation(summary = "get books", description = "get all book")
    public List<BookView> getBooks(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        return bs.getAll(page, size);
    }

    @GetMapping("/{uid}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
    @Operation(summary = "get book details", description = "get book details based on uid")
    public Book getBook(@PathVariable String uid) {
        return bs.getByUid(uid);
    }

    @GetMapping("/category")
    @Operation(summary = "get books with category", description = "get all book based on category")
    public List<BookView> getBooksByCategory(
            @RequestParam String category,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        return bs.getByCategory(category, page, size);
    }

    @GetMapping("/search")
    @Operation(summary = "get books with title", description = "get all book based on title that match search query")
    public List<BookView> search(
            @RequestParam String title,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        return bs.getByTitle(title, page, size);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "409", description = "DATA_CONFLICT", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "add new book", description = "add new book data into system")
    public Book create(@RequestBody Book book) {
        return bs.insert(book);
    }

    @PutMapping("/{uid}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
    @Operation(summary = "update book", description = "update book data based on uid")
    public Book update(@PathVariable String uid, @RequestBody Book book) {
        return bs.update(uid, book);
    }

    @DeleteMapping("/{uid}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
    @Operation(summary = "delete book", description = "delete book data based on uid")
    public void delete(@PathVariable String uid) {
        bs.delete(uid);
    }

}
