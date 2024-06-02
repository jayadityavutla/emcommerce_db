package com.example.demo_ecommerce.service;

import com.example.demo_ecommerce.dto.ProductDTO;
import com.example.demo_ecommerce.exception.ProductNotFoundException;
import com.example.demo_ecommerce.model.Product;
import com.example.demo_ecommerce.repository.projection.ProductProjection;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product getProductById(long id);
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    List<Product> getProductByCategory(Long id);
    List<ProductProjection> getProductsByCategoryIdProjection(Long id);

    Product updateProduct(Long id, Product product) throws ProductNotFoundException;
    String deleteProductById(Long id) throws ProductNotFoundException;
}
