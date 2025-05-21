package com.ecommerce.Ecomerce.Dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CartItemDTO {
    private UUID itemId;
    private UUID productId;
    private String productName;
    private Double unitPrice;
    private Integer quantity;

    private List<ProductAttributeDTO2> attributes;
    private List<ProductGalleryDTO> gallery;
}
