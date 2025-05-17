package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.Coupon;
import com.ecommerce.Ecomerce.Repository.CouponRepository;
import com.ecommerce.Ecomerce.Service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public List<Coupon> getAll() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon getById(UUID id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy Coupon với ID: " + id));
    }

    @Override
    public Coupon create(Coupon coupon) {
        coupon.setTimesUsed(0);
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon update(UUID id, Coupon newCoupon) {
        Coupon existing = getById(id);
        existing.setCode(newCoupon.getCode());
        existing.setDiscountValue(newCoupon.getDiscountValue());
        existing.setDiscountType(newCoupon.getDiscountType());
        existing.setMaxUsage(newCoupon.getMaxUsage());
        existing.setOrderAmountLimit(newCoupon.getOrderAmountLimit());
        existing.setCouponStartDate(newCoupon.getCouponStartDate());
        existing.setCouponEndDate(newCoupon.getCouponEndDate());
        // timesUsed giữ nguyên hoặc có logic tùy bạn
        return couponRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        couponRepository.deleteById(id);
    }
}
