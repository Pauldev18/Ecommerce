package com.ecommerce.Ecomerce.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplyVoucherResponse {
    private boolean success;
    private String message;
    private Double discountAmount;
}

