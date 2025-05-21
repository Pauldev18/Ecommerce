package com.ecommerce.Ecomerce.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CartItemDTO {
    private UUID itemId;
    private UUID productId;
    private Integer quantity;
}
