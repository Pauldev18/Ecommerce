package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}