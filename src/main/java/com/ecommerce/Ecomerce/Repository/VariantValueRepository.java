package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.VariantValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VariantValueRepository extends JpaRepository<VariantValue, UUID> {
}