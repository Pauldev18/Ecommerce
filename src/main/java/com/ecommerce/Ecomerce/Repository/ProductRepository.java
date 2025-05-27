package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("""
       SELECT pc.product 
       FROM ProductCategory pc 
       WHERE pc.category.id = :categoryId
    """)
    List<Product> findAllByCategoryId(@Param("categoryId") UUID categoryId);
}