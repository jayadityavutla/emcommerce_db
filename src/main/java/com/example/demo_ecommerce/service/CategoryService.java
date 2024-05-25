package com.example.demo_ecommerce.service;

import com.example.demo_ecommerce.model.Product;

import java.util.List;

public interface CategoryService {
    List<String> getAllCategories();
    List<Product> getProductsByCategoryTitle(String catTitle);
}
