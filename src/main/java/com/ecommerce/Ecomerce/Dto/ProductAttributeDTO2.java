package com.ecommerce.Ecomerce.Dto;

import com.ecommerce.Ecomerce.Entity.ProductAttribute;
import com.ecommerce.Ecomerce.Entity.ProductAttributeValue;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class ProductAttributeDTO2 {
    private UUID id;
    private UUID attributeId;
    private String name;
    private List<AttributeValueDTO> values;

    public static ProductAttributeDTO2 fromEntity(ProductAttribute pa) {
        ProductAttributeDTO2 dto = new ProductAttributeDTO2();
        dto.setId(pa.getId());
        dto.setAttributeId(pa.getAttribute() != null ? pa.getAttribute().getId() : null);
        dto.setName(pa.getAttribute() != null ? pa.getAttribute().getName() : null);
        dto.setValues(
                pa.getValues() != null
                        ? pa.getValues().stream()
                        .map(pav -> AttributeValueDTO.fromEntity(pav.getAttributeValue()))
                        .collect(Collectors.toList())
                        : Collections.emptyList()
        );
        return dto;
    }
}
