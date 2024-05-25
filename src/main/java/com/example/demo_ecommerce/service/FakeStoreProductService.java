package com.example.demo_ecommerce.service;

import com.example.demo_ecommerce.dto.CategoryDTO;
import com.example.demo_ecommerce.dto.FakeStoreCategoryDTO;
import com.example.demo_ecommerce.dto.FakeStoreProductDTO;
import com.example.demo_ecommerce.exception.ProductNotFoundException;
import com.example.demo_ecommerce.model.Category;
import com.example.demo_ecommerce.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product createProduct(Product product){
        FakeStoreProductDTO fs = new FakeStoreProductDTO();
        fs.setId(product.getId());
        fs.setTitle(product.getTitle());
        fs.setCategory(product.getCategory().getTitle());
        fs.setImage(product.getImageUrl());
        fs.setDescription(product.getDescription());
        fs.setPrice(product.getPrice());

        FakeStoreProductDTO response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fs,
                FakeStoreProductDTO.class
        );

        return response.toProduct();
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException{
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDTO.class
        );

        if(fakeStoreProductDTO == null){
            throw new ProductNotFoundException("product not found with id "+ productId);
        }
        return fakeStoreProductDTO.toProduct();
    }

    @Override
    public List<Product> getAllProducts(){
        FakeStoreProductDTO[] res = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class
        );
        List<Product> product = new ArrayList<>();
        for(FakeStoreProductDTO fks : res){
            product.add(fks.toProduct());
        }

        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException{
        FakeStoreProductDTO fs = new FakeStoreProductDTO();
        fs.setId(product.getId());
        fs.setTitle(product.getTitle());
        fs.setCategory(product.getCategory().getTitle());
        fs.setImage(product.getImageUrl());
        fs.setDescription(product.getDescription());
        fs.setPrice(product.getPrice());

        restTemplate.put(
                "https://fakestoreapi.com/products/" + id,
                fs
        );

        //We do not get response from PUT method. So get product by id
        Product response = getSingleProduct(id);
        if(response == null){
            throw new ProductNotFoundException("Product not found with id "+ id);
        }
        return response;
    }

    public void deleteProduct(Long id){
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }

}
