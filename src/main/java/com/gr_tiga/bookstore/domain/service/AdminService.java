package com.gr_tiga.bookstore.domain.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr_tiga.bookstore.domain.repository.AdminRepository;
import com.gr_tiga.bookstore.helper.exception.DataAlreadyExistsException;
import com.gr_tiga.bookstore.helper.exception.DataNotFoundException;
import com.gr_tiga.bookstore.model.table.Admin;

@Service
@Transactional
public class AdminService {

    private final AdminRepository ar;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AdminService(AdminRepository ar) {
        this.ar = ar;
    }

    public Admin getById(Integer id) {
        return ar.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Admin", id.toString()));
        // ✅ findById returns Optional<Admin>, so .orElseThrow() works here
    }

    public Admin insert(Admin admin) {
        if (ar.existsByEmail(admin.getEmail())) {
            throw new DataAlreadyExistsException("Admin", "Email already exists");
        } else if (ar.existsByUsername(admin.getUsername())) {
            throw new DataAlreadyExistsException("Admin", "Username already used");
        } else {
            admin.setPassword(encoder.encode(admin.getPassword()));
            return ar.save(admin);
        }
    }

    public Admin login(String email, String password) {
        if (!ar.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is not registered!");
        } else {
            Admin admin = ar.findByEmail(email);  
            // ✅ findByEmail returns Admin directly, NO .orElseThrow()

            if (!encoder.matches(password, admin.getPassword())) {
                throw new IllegalArgumentException("Password does not match!");
            } else {
                return admin;
            }
        }
    }

    public Admin update(Integer id, Admin adminDetails) {
        Admin oldAdmin = getById(id);

        oldAdmin.setUsername(adminDetails.getUsername());
        oldAdmin.setEmail(adminDetails.getEmail());
        
        //if (adminDetails.getPassword() != null && !adminDetails.getPassword().isEmpty()) {
        //    oldAdmin.setPassword(encoder.encode(adminDetails.getPassword()));
       // }

        return ar.save(oldAdmin);
    }

    public void delete(Integer id) {
        if (!ar.existsById(id)) {
            throw new DataNotFoundException("Admin", id.toString());
        } else {
            ar.deleteById(id);
        }
    }
}