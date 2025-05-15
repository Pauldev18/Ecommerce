package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VariantRepository extends JpaRepository<Variant, UUID> {
}