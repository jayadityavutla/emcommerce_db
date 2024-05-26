package com.example.demo_ecommerce.service;

import com.example.demo_ecommerce.exception.ProductNotFoundException;
import com.example.demo_ecommerce.model.Category;
import com.example.demo_ecommerce.model.Product;
import com.example.demo_ecommerce.repository.projection.ProductProjection;
import org.springframework.stereotype.Service;
import com.example.demo_ecommerce.repository.CategoryRepository;
import com.example.demo_ecommerce.repository.ProductRepository;

import java.util.List;
import java.util.Optional;


@Service("productService")
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product createProduct(Product product) {
        // Lets say we are not passing category ID in our request body
        Category cat = categoryRepository.findByTitle(product.getCategory().getTitle());
        if(cat == null) {
            // No category with our title in the database
            Category newCat = new Category();
            newCat.setTitle(product.getCategory().getTitle());
            Category newRow = categoryRepository.save(newCat);
            // newRow will have now catId
            product.setCategory(newRow);
        } else {
            product.setCategory(cat);
        }

        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product getProductById(long id) {
        return new Product();
        //return productRepository.findById(id);
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(productId);
        if(p.isPresent()) {
            return p.get();
        }
        throw new ProductNotFoundException("Product not found");
    }

    @Override
    public List<Product> getProductByCategory(Long id){
        List<Product> productList = productRepository.findProductByCategory(id);
        return productList;
    }

    @Override
    public List<ProductProjection> getProductsByCategoryIdProjection(Long id){
        return productRepository.getProductsByCategoryIdProjection(id);
    }
}
