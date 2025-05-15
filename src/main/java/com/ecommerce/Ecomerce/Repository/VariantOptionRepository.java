package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.VariantOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VariantOptionRepository extends JpaRepository<VariantOption, UUID> {
}