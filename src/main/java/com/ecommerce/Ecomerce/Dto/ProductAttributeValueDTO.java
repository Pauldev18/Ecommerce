package com.ecommerce.Ecomerce.Dto;

import com.ecommerce.Ecomerce.Entity.ProductAttributeValue;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductAttributeValueDTO {
    private UUID id;
    private UUID attributeValueId;
    private String value;

    public static ProductAttributeValueDTO fromEntity(ProductAttributeValue pav) {
        ProductAttributeValueDTO dto = new ProductAttributeValueDTO();
        dto.setId(pav.getId());
        dto.setAttributeValueId(pav.getAttributeValue() != null ? pav.getAttributeValue().getId() : null);
        dto.setValue(pav.getAttributeValue() != null ? pav.getAttributeValue().getValue() : null);
        return dto;
    }
}
