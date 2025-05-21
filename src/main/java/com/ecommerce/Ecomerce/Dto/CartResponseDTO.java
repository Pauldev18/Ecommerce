package com.ecommerce.Ecomerce.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CartResponseDTO {
    private UUID cardId;
    private UUID customerId;
    private List<CartItemDTO> items;
}
