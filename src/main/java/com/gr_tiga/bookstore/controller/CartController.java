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
import com.gr_tiga.bookstore.model.table.BookCart;
import com.gr_tiga.bookstore.model.view.BookCartView;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    
    private final CartService cs;

    public CartController(CartService cs){
        this.cs = cs;
    }

    @GetMapping
    public Object getCart(@RequestParam String uid){
        return cs.find(uid);
    }


    @GetMapping("/item")
    public List<BookCartView> getCartItem(@RequestParam String cuid){
        return cs.getByCartId(cuid);
    }

    @PostMapping("/item")
    public BookCart addCartItem(@RequestBody BookCart bookCart){
        return cs.insertItem(bookCart);
    }

    @PutMapping("/item")
    public BookCart updateCartItem(@RequestBody BookCart bookCart){
        return cs.updateItem(bookCart);
    }

    @DeleteMapping("/item/{id}")
    public void deleteCartItem(@PathVariable Integer id){
        cs.deleteItem(id);
    }
}
