package com.example.demo_ecommerce.repository;

import com.example.demo_ecommerce.model.Product;
import com.example.demo_ecommerce.repository.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    Product findById(long id);

    //HQL
    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> findProductsByCategoryId(@Param("categoryId") Long categoryId);

    //Native Query
    @Query(value="select * from Product p where p.category_id = :categoryId", nativeQuery = true)
    List<Product> findProductByCategory(@Param("categoryId") Long categoryId);

    @Query("select p.id as id, p.title as title from Product p where p.category.id = :categoryId")
    List<ProductProjection> getProductsByCategoryIdProjection(@Param("categoryId") Long categoryId);
}
