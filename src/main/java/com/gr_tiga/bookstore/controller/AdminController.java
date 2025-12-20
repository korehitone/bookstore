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

    @GetMapping
    public Admin getAdminProfile(){
        return as.getAdmin();
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin){
        return as.create(admin);
    }

    @PutMapping
    public Admin updateAdminProfile(@RequestBody Admin admin){
        Admin existingAdmin = as.getAdmin();
        return as.update(existingAdmin.getId(), admin);
    }

    @DeleteMapping
    public void deleteAdminAccount(){
        Admin admin = as.getAdmin();
            as.delete(admin.getId());
        }
}
