package com.example.demo_ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private Category category;


}
