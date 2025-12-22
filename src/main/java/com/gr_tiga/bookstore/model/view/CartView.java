package com.gr_tiga.bookstore.model.view;

import java.math.BigDecimal;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.*;

@Entity
@Immutable
@Table(name = "cart_view")
public class CartView {

    @Id
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String uid;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "total_price")
    private BigDecimal totalPrice;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
