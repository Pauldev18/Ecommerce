package com.ecommerce.Ecomerce.Dto;

import lombok.Data;
import java.util.UUID;

@Data
public class ProductCategoryDTO {
    private UUID id;
    private UUID productId;
    private UUID categoryId;
    private String productName;
    private String categoryName;
}