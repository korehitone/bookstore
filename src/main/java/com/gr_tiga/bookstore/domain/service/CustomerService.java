package com.gr_tiga.bookstore.domain.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gr_tiga.bookstore.domain.repository.CustomerRepository;
import com.gr_tiga.bookstore.helper.exception.DataAlreadyExistsException;
import com.gr_tiga.bookstore.helper.exception.DataNotFoundException;
import com.gr_tiga.bookstore.model.table.Customer;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository cr;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final CartService cs;

    public CustomerService(CustomerRepository cr, CartService cs) {
        this.cr = cr;
        this.cs = cs;
    }

    public Customer getByUid(String uid) {
        return cr.findByUid(uid)
                .orElseThrow(() -> new DataNotFoundException("Customer", uid));
    }

    public Customer insert(Customer customer) {
        if (cr.existsByEmail(customer.getEmail())) {
            throw new DataAlreadyExistsException("Customer", "Email already exists");
        } else if (cr.existsByUsername(customer.getUsername())) {
            throw new DataAlreadyExistsException("Customer", "Username already used");
        } else {
            customer.setPassword(encoder.encode(customer.getPassword()));
            Customer c = cr.save(customer);
            cs.insert(c.getId());
            return c;
        }
    }

    public Customer login(String email, String password){
        if (!cr.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is not registered!");  
        } else {
            Customer c = cr.findByEmail(email);

            if(!encoder.matches(password, c.getPassword())){
                throw new IllegalArgumentException("Password is not match!");
            } else {
                return c;
            }
        }
    }

    public Customer update(String uid, Customer customer) {
        Customer oldCustomer = getByUid(uid);

        oldCustomer.setUsername(customer.getUsername());
        oldCustomer.setAddress(customer.getAddress());

        return cr.save(oldCustomer);
    }

    public void delete(String uid) {
        if (!cr.existsByUid(uid)) {
            throw new DataNotFoundException("Customer", uid);
        } else {
            cr.deleteByUid(uid);
        }
    }
}
