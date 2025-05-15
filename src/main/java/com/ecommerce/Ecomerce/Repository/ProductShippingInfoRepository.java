package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.ProductShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductShippingInfoRepository extends JpaRepository<ProductShippingInfo, UUID> {
}