package com.example.demo_ecommerce.service;

import com.example.demo_ecommerce.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(Long id);
    List<Category> findAllCategories();
}
