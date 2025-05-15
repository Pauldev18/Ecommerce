package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.ShippingZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShippingZoneRepository extends JpaRepository<ShippingZone, UUID> {
}