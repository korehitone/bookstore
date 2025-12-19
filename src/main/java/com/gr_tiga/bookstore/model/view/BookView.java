package com.gr_tiga.bookstore.model.view;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.*;

@Entity
@Immutable
@Table(name = "book_view")
public class BookView {

    @Id
    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String img_url;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;



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

    public Integer getPrice() {
        return price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
