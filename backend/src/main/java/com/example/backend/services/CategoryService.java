package com.example.backend.services;

import com.example.backend.models.Category;
import com.example.backend.models.Projects;
import com.example.backend.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> get_category_list() {
        return Streamable.of(this.categoryRepository.findAll()).toList();
    }
    
    public Category get_by_id(Long id) {
        return this.categoryRepository.findById(id).get();
    }
}
