package com.ecommerce.Ecomerce.Dto;

import lombok.Data;

@Data
public class ApplyVoucherRequest {
    private String couponCode;
    private Double orderTotal;
}

