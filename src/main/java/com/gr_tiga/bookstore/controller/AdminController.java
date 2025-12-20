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

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin){
        return as.create(admin);
    }

    @GetMapping("/{id}")
    public Admin getAdminProfile(@PathVariable Integer id){
        return as.getById(id);
    }

    @PutMapping("/{id}")
    public Admin updateAdminProfile(@PathVariable Integer id, @RequestBody Admin admin){
        return as.update(id, admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdminAccount(@PathVariable Integer id){
        as.delete(id);
    }
}
