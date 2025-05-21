package com.ecommerce.Ecomerce.Dto;

import lombok.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequestDTO {
    private String productId;
    private Integer quantity;
}