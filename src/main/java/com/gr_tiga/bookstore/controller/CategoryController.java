package com.gr_tiga.bookstore.controller;

import org.springframework.web.bind.annotation.*;

import com.gr_tiga.bookstore.model.table.Category;
import com.gr_tiga.bookstore.domain.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    
    private final CategoryService cs;

    public CategoryController(CategoryService cs){
        this.cs = cs;
    }

    @GetMapping
    public List<Category> getCategories(){
        return cs.getAll();
    }

    @GetMapping("/{id}")
        public Category getCategory(@PathVariable Integer id){
            return cs.getById(id);
        }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return cs.create(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Integer id, @RequestBody Category category){
        return cs.update(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id){
        cs.delete(id);
    }
}
