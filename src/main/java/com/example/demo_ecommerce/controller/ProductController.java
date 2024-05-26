package com.example.demo_ecommerce.controller;

import com.example.demo_ecommerce.exception.ProductNotFoundException;
import com.example.demo_ecommerce.model.Product;
import com.example.demo_ecommerce.repository.projection.ProductProjection;
import com.example.demo_ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

//    @GetMapping("products/{id}")
//    public Product getProductById(@RequestParam("id") int id) {
//        return productService.getProductById(id);
//    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        // Whenever someone is doing a get request on /product/{id}
        // Plz execute this method
        Product currentProduct = productService.getSingleProduct(productId);
        ResponseEntity<Product> res = new ResponseEntity<>(
                currentProduct, HttpStatus.OK);

        //throw new RuntimeException();
        return res;
        //throw new RuntimeException();
        //return currentProduct;
    }

    @GetMapping("products/category/{id}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("id") Long productId) throws ProductNotFoundException {
        List<Product> productList = productService.getProductByCategory(productId);
        ResponseEntity<List<Product>> res = new ResponseEntity<>(productList, HttpStatus.OK);
        return res;
    }

    @GetMapping("products/category-custom/{id}")
    public ResponseEntity<List<ProductProjection>> getProductsByCategoryIdProjection(@PathVariable("id") Long productId) throws ProductNotFoundException {
        List<ProductProjection> productList = productService.getProductsByCategoryIdProjection(productId);
        ResponseEntity<List<ProductProjection>> res = new ResponseEntity<>(productList, HttpStatus.OK);
        return res;
    }

}
