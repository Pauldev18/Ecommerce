package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.ProductCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCouponRepository extends JpaRepository<ProductCoupon, UUID> {
}