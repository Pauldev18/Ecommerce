package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellRepository extends JpaRepository<Sell, UUID> {
}