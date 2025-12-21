package com.gr_tiga.bookstore.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gr_tiga.bookstore.domain.repository.BookCartRepository;
import com.gr_tiga.bookstore.domain.repository.BookCartViewRepository;
import com.gr_tiga.bookstore.domain.repository.CartRepository;
import com.gr_tiga.bookstore.domain.repository.CartViewRepository;
import com.gr_tiga.bookstore.domain.repository.CustomerRepository;
import com.gr_tiga.bookstore.helper.exception.DataNotFoundException;
import com.gr_tiga.bookstore.model.table.BookCart;
import com.gr_tiga.bookstore.model.table.Cart;
import com.gr_tiga.bookstore.model.table.Customer;
import com.gr_tiga.bookstore.model.view.BookCartView;
import com.gr_tiga.bookstore.model.view.CartView;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartService {

    private final CartRepository cr;
    private final CartViewRepository cvr;
    private final BookCartRepository bcr;
    private final BookCartViewRepository bcvr;
    private final CustomerRepository cs;

    public CartService(CartRepository cr, CartViewRepository cvr, BookCartRepository bcr, BookCartViewRepository bcvr, CustomerRepository cs) {
        this.cr = cr;
        this.cvr = cvr;
        this.bcr = bcr;
        this.bcvr = bcvr;
        this.cs = cs;
    }

    public void insert(Integer custId) {
        Cart c = new Cart(custId);
        cr.save(c);
    }

    public CartView findByCartView(Integer custId) {
        cs.findById(custId).orElseThrow(() -> new DataNotFoundException("Customer", custId.toString()));
        return cvr.findByCustomerId(custId);
    }

    public Cart findByCart(Integer custId) {
        return cr.findByCustomerId(custId);
    }

    public Object find(String uid) {
        Customer c = cs.findByUid(uid).orElseThrow(() -> new DataNotFoundException("Customer", uid));

        CartView cv = findByCartView(c.getId());
        Cart ca = findByCart(c.getId());

        if (cv != null) {
            return cv;
        } else {
            return ca;
        }
    }

    public List<BookCartView> getByCartId(String cartUid){
        Cart c = cr.findByUid(cartUid).orElseThrow(() -> new DataNotFoundException("Customer", cartUid));
        return bcvr.findByCartId(c.getId());
    }

    public BookCart insertItem(BookCart bookCart){
        return bcr.save(bookCart);
    }

    public BookCart updateItem(BookCart bookCart){
        BookCart bc = bcr.findByCartIdAndBookId(bookCart.getCartId(), bookCart.getBookId());
        if(bc == null){
            throw new DataNotFoundException("BookCart", (bookCart.getCartId().toString() + " & " + bookCart.getBookId().toString()));
        } else {
            bc.setQuantity(bookCart.getQuantity());
            return bcr.save(bc);
        }
    }

    public void deleteItem(Integer id) {
        if (!bcr.existsById(id)) {
            throw new DataNotFoundException("BookCart", id.toString());
        } else {
            bcr.deleteById(id);;
        }
    }

}
