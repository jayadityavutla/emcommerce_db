package com.example.demo_ecommerce.dto;

import java.util.List;

public class CategoryDTO {
    private String title;
    private List<ProductDTO> products;

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "title='" + title + '\'' +
                ", products=" + products +
                '}';
    }
}
