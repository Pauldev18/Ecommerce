package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.Coupon;

import java.util.List;
import java.util.UUID;

public interface CouponService {
    List<Coupon> getAll();
    Coupon getById(UUID id);
    Coupon create(Coupon coupon);
    Coupon update(UUID id, Coupon coupon);
    void delete(UUID id);
}
