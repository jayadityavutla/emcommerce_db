package com.example.demo_ecommerce.controller;

import com.example.demo_ecommerce.model.Product;
import com.example.demo_ecommerce.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<String> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{title}")
    public List<Product> getProductsByCategoryTitle(@PathVariable("title") String title){
        return categoryService.getProductsByCategoryTitle(title);
    }

}
