package com.example.demo_ecommerce.repository;

import com.example.demo_ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);

    Category findByTitle(String title);
}
