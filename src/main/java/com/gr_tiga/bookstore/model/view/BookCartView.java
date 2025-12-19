package com.gr_tiga.bookstore.model.view;

import java.math.BigDecimal;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.*;

@Entity
@Immutable
@Table(name = "book_cart_view")
public class BookCartView {
    
    @Id
    @Column(nullable = false)
    private Integer id;

    @Column(name = "cart_id", nullable = false)
    private Integer cartId;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String img_url;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;


    
    public Integer getId() {
        return id;
    }

    public Integer getCartId() {
        return cartId;
    }

    public String getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImg_url() {
        return img_url;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
