package com.gr_tiga.bookstore.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.PageRequest;
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

    public CartService(CartRepository cr, CartViewRepository cvr, BookCartRepository bcr, BookCartViewRepository bcvr,
            CustomerRepository cs) {
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
        return cvr.findByCustomerId(custId);
    }

    public Cart findByCart(Integer custId) {
        return cr.findByCustomerId(custId);
    }

    public CartView find(String uid) {
        Customer c = cs.findByUid(uid).orElseThrow(() -> new DataNotFoundException("Customer", uid));

        CartView cv = findByCartView(c.getId());
        Cart ca = findByCart(c.getId());

        if (cv != null) {
            return cv;
        } else {
            CartView cvn = new CartView();

            cvn.setId(ca.getId());
            cvn.setUid(ca.getUid());
            cvn.setCustomerId(ca.getCustomerId());
            cvn.setTotalPrice(new BigDecimal(0));

            return cvn;
        }
    }

    public List<BookCartView> getByCartId(String cartUid, Integer page, Integer size) {

        int p = (page != null && page >= 0) ? page : 0;
        int s = (size != null && size >= 0) ? size : 25;

        Cart c = cr.findByUid(cartUid).orElseThrow(() -> new DataNotFoundException("Customer", cartUid));
        return bcvr.findByCartId(c.getId(), PageRequest.of(p, s)).getContent();
    }

    public BookCart insertItem(BookCart bookCart) {
        return bcr.save(bookCart);
    }

    public BookCart updateItem(BookCart bookCart) {
        BookCart bc = bcr.findByCartIdAndBookId(bookCart.getCartId(), bookCart.getBookId());
        if (bc == null) {
            throw new DataNotFoundException("BookCart",
                    (bookCart.getCartId().toString() + " & " + bookCart.getBookId().toString()));
        } else {
            bc.setQuantity(bookCart.getQuantity());
            return bcr.save(bc);
        }
    }

    public void deleteItem(Integer id) {
        if (!bcr.existsById(id)) {
            throw new DataNotFoundException("BookCart", id.toString());
        } else {
            bcr.deleteById(id);
            ;
        }
    }

}
