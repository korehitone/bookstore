package com.gr_tiga.bookstore.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gr_tiga.bookstore.domain.repository.CategoryRepository;
import com.gr_tiga.bookstore.model.table.Category;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository cr;

    public CategoryService(CategoryRepository cr){
        this.cr = cr;
    }

    public List<Category> getAll(){
        return cr.findAll();
    }

    public Category getById(Integer id){
        return cr.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public Category create(Category category){
        return cr.save(category);
    }

    public Category update(Integer id, Category categoryDetails){
        Category category = getById(id);
        category.setName(categoryDetails.getName());
        return cr.save(category);
    }

    public void delete(Integer id){
        Category category = getById(id);
        cr.delete(category);
    }
    
}
