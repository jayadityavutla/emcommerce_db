package com.example.demo_ecommerce.service;

import com.example.demo_ecommerce.dto.CategoryDTO;
import com.example.demo_ecommerce.dto.FakeStoreCategoryDTO;
import com.example.demo_ecommerce.exception.ProductNotFoundException;
import com.example.demo_ecommerce.model.Category;
import com.example.demo_ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product) throws ProductNotFoundException;
    void deleteProduct(Long id);
}
