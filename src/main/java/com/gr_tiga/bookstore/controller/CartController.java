package com.gr_tiga.bookstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gr_tiga.bookstore.domain.service.CartService;
import com.gr_tiga.bookstore.helper.ErrorResponse;
import com.gr_tiga.bookstore.model.table.BookCart;
import com.gr_tiga.bookstore.model.view.BookCartView;
import com.gr_tiga.bookstore.model.view.CartView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cs;

    public CartController(CartService cs) {
        this.cs = cs;
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CartView.class)) }),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "get cart by customer uid", description = "get cart that customer have based on customer uid")
    public CartView getCart(@RequestParam String uid) {
        return cs.find(uid);
    }

    @GetMapping("/item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookCartView.class))) }),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "get cart item", description = "get all cart item")
    public List<BookCartView> getCartItem(
            @RequestParam String cuid,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        return cs.getByCartId(cuid, page, size);
    }

    @PostMapping("/item")
    @Operation(summary = "add new cart item", description = "add new item into cart")
    public BookCart addCartItem(@RequestBody BookCart bookCart) {
        return cs.insertItem(bookCart);
    }

    @PutMapping("/item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BookCart.class)) }),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "update cart item", description = "update cart item data based on id")
    public BookCart updateCartItem(@RequestBody BookCart bookCart) {
        return cs.updateItem(bookCart);
    }

    @DeleteMapping("/item/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "DATA_NOT_FOUND", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @Operation(summary = "delete cart item", description = "remove item from cart based on cart item id")
    public void deleteCartItem(@PathVariable Integer id) {
        cs.deleteItem(id);
    }
}
