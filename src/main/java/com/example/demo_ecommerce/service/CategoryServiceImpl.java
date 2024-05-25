package com.example.demo_ecommerce.service;

import com.example.demo_ecommerce.dto.FakeStoreProductDTO;
import com.example.demo_ecommerce.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    private RestTemplate restTemplate;

    public CategoryServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> getAllCategories(){
        String[] res = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );
        //return Arrays.asList(res);
        List<String> categoryList = new ArrayList<>();
        for(String  fks : res){
            categoryList.add(fks);
        }
        return categoryList;
    }

    @Override
    public List<Product> getProductsByCategoryTitle(String catTitle){
        FakeStoreProductDTO[] res = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + catTitle,
                FakeStoreProductDTO[].class
        );
        List<Product> productList = new ArrayList<>();
        for(FakeStoreProductDTO fks : res){
            productList.add(fks.toProduct());
        }
        return productList;
    }
}
