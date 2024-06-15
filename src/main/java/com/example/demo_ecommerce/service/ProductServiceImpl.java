package com.example.demo_ecommerce.service;

import com.example.demo_ecommerce.dto.ProductDTO;
import com.example.demo_ecommerce.exception.ProductNotFoundException;
import com.example.demo_ecommerce.model.Category;
import com.example.demo_ecommerce.model.Product;
import com.example.demo_ecommerce.model.ProductMapper;
import com.example.demo_ecommerce.repository.projection.ProductProjection;
import org.springframework.stereotype.Service;
import com.example.demo_ecommerce.repository.CategoryRepository;
import com.example.demo_ecommerce.repository.ProductRepository;

import java.util.List;
import java.util.Optional;


@Service("productService")
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product createProduct(Product product) throws ProductNotFoundException{
        // Lets say we are not passing category ID in our request body
        Product response = new Product();
        try{
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
            response = productRepository.save(product);
            }
        catch(Exception e){
            e.getStackTrace();
            throw new ProductNotFoundException("Product not found");
        }

        return response;
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
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
        return productRepository.findProductByCategory(id);
    }

    @Override
    public List<ProductProjection> getProductsByCategoryIdProjection(Long id){
        return productRepository.getProductsByCategoryIdProjection(id);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException{
        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        Product existingDbProduct = p.get();
        if(product.getTitle() != null){
            existingDbProduct.setTitle(product.getTitle());
        }
        if(product.getDescription() != null){
            existingDbProduct.setDescription(product.getDescription());
        }
        return productRepository.save(existingDbProduct);
    }

    @Override
    public String deleteProductById(Long id) throws ProductNotFoundException{
        int recCount = productRepository.deleteProductById(id);
        if(recCount > 0){
            return "Product deleted successfully";
        }
        throw new ProductNotFoundException("Product not found");
    }
}
