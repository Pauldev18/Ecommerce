package com.ecommerce.Ecomerce.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductAttributeValueReponse {
    private UUID id;
    private UUID productAttributeId;
    private String productAttributeName;

    private UUID attributeValueId;
    private String attributeValueName;
}
