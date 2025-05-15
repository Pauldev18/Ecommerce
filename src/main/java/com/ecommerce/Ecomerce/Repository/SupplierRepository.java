package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}