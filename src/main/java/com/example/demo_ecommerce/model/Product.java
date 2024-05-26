package com.example.demo_ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    @ManyToOne
    private Category category;


}
