package com.gr_tiga.bookstore.controller;

import org.springframework.web.bind.annotation.*;

import com.gr_tiga.bookstore.model.table.Admin;
import com.gr_tiga.bookstore.domain.service.AdminService;

// import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    private final AdminService as;

    public AdminController(AdminService as){
        this.as = as;
    }

    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable Integer id){
        return as.getById(id);
    }

    @PostMapping
    public Admin signUp(@RequestBody Admin admin){
        return as.insert(admin);
    }

    @GetMapping("/login")
    public Admin signIn(@RequestHeader String email, @RequestHeader String password){
        return as.login(email, password);
    }

    @PutMapping("/{id}")
    public Admin update(@PathVariable Integer id, @RequestBody Admin admin){
        return as.update(id, admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        as.delete(id);
    }
}
