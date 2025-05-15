package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, UUID> {
}