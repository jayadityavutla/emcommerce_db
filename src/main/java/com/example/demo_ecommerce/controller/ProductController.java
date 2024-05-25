package com.example.demo_ecommerce.controller;

import com.example.demo_ecommerce.dto.CategoryDTO;
import com.example.demo_ecommerce.dto.ErrorDto;
import com.example.demo_ecommerce.dto.FakeStoreCategoryDTO;
import com.example.demo_ecommerce.exception.ProductNotFoundException;
import com.example.demo_ecommerce.model.Category;
import com.example.demo_ecommerce.model.Product;
import com.example.demo_ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/testpath")
    public String getTestMethod(){
        return "Hello World";
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) throws ProductNotFoundException {
        Product curProduct = productService.getSingleProduct(productId);
        ResponseEntity<Product> res = new ResponseEntity<>(curProduct, HttpStatus.OK);
        return res;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotFoundException{
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e){
        ErrorDto errordto = new ErrorDto();
        errordto.setMessage(e.getMessage());
        return new ResponseEntity<>(errordto, HttpStatus.NOT_FOUND);
    }
}
