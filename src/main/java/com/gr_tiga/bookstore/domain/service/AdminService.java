package com.gr_tiga.bookstore.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr_tiga.bookstore.domain.repository.AdminRepository;
import com.gr_tiga.bookstore.model.table.Admin;

// import java.util.List;

@Service
@Transactional
public class AdminService {

    private final AdminRepository ar;

    public AdminService(AdminRepository ar){
        this.ar = ar;
    }
    
    public Admin getAdmin(){
        return ar.findAll().get(0);
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
        admin.setPassword(adminDetails.getPassword());
        return ar.save(admin);
    }

    public void delete(Integer id){
        Admin admin = getById(id);
        ar.delete(admin);
    }
}
