package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.ProductAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValue, UUID> {
}