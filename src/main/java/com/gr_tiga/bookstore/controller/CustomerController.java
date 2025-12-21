package com.gr_tiga.bookstore.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gr_tiga.bookstore.domain.service.CustomerService;
import com.gr_tiga.bookstore.model.table.Customer;

@RestController
@RequestMapping("/api/user")
public class CustomerController {
    
    private final CustomerService cs;

    public CustomerController(CustomerService cs){
        this.cs = cs;
    }

    @GetMapping("/{uid}")
    public Customer getCustomer(@PathVariable String uid){
        return cs.getByUid(uid);
    }

    @PostMapping
    public Customer signUp(@RequestBody Customer customer){
        return cs.insert(customer);
    }

    @GetMapping("/login")
    public Customer signIn(@RequestHeader String email, @RequestHeader String password){
        return cs.login(email, password);
    }

    @PutMapping("/{uid}")
    public Customer update(@PathVariable String uid, @RequestBody Customer customer){
        return cs.update(uid, customer);
    }

    @DeleteMapping("/{uid}")
    public void delete(@PathVariable String uid){
        cs.delete(uid);
    }
}
