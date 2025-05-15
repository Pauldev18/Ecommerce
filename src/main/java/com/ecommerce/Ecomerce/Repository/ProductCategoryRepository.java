package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
}