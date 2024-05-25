package com.example.demo_ecommerce.dto;

import com.example.demo_ecommerce.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FakeStoreCategoryDTO {
    private Long id;
    private String title;

    public Category toCategory(){
        Category cat = new Category();
        cat.setId(this.id);
        cat.setTitle(this.title);

        return cat;
    }
}
