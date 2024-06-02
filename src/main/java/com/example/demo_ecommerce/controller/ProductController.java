package com.example.demo_ecommerce.controller;

import com.example.demo_ecommerce.dto.ErrorDto;
import com.example.demo_ecommerce.dto.ProductDTO;
import com.example.demo_ecommerce.exception.ProductNotFoundException;
import com.example.demo_ecommerce.model.Product;
import com.example.demo_ecommerce.repository.ProductRepository;
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
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

//    @GetMapping("products/{id}")
//    public Product getProductById(@RequestParam("id") int id) {
//        return productService.getProductById(id);
//    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        Product currentProduct = productService.getSingleProduct(productId);
        ResponseEntity<Product> res = new ResponseEntity<>(
                currentProduct, HttpStatus.OK);
        return res;
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

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException{
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException{
         String str = productService.deleteProductById(productId);
         return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e){
        ErrorDto errordto = new ErrorDto();
        errordto.setMessage(e.getMessage());
        return new ResponseEntity<>(errordto, HttpStatus.NOT_FOUND);
    }

}
