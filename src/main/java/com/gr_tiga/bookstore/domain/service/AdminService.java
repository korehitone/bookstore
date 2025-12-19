package com.gr_tiga.bookstore.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr_tiga.bookstore.domain.repository.AdminRepository;
import com.gr_tiga.bookstore.model.table.Admin;

@Service
@Transactional
public class AdminService {

    private final AdminRepository ar;

    public AdminService(AdminRepository ar){
        this.ar = ar;
    }
    
    public List<Admin> getAll(){
        return ar.findAll();
    }

    public Admin getById(Integer id){
        return ar.findById(id)
            .orElseThrow(() -> new RuntimeException ("Admin not found with id: " + id));
    }

    public Admin create(Admin admin){
        return ar.save(admin);
    }

    public Admin update(Integer id, Admin adminDetails){
        Admin admin = getById(id);
        admin.setUsername(adminDetails.getUsername());
        admin.setEmail(adminDetails.getEmail());
        return ar.save(admin);
    }

    public void delete(Integer id){
        Admin admin = getById(id);
        ar.delete(admin);
    }
}
