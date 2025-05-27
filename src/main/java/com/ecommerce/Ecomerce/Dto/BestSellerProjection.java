package com.ecommerce.Ecomerce.Dto;

import com.ecommerce.Ecomerce.Entity.Product;

public interface BestSellerProjection {
    Product getProduct();
    Long getTotalQuantity();
}

