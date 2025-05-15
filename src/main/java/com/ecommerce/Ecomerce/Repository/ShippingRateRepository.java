package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.ShippingRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShippingRateRepository extends JpaRepository<ShippingRate, UUID> {
}