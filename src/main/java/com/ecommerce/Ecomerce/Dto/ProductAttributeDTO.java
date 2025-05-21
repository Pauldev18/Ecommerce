package com.ecommerce.Ecomerce.Dto;

import com.ecommerce.Ecomerce.Entity.ProductAttribute;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class ProductAttributeDTO {
    private UUID id;
    private UUID productId;
    private String productName;
    private UUID attributeId;
    private String attributeName;
    private List<AttributeValueDTO> attributeValues;

    public ProductAttributeDTO() {}

    public ProductAttributeDTO(UUID id, UUID productId, String productName,
                               UUID attributeId, String attributeName,
                               List<AttributeValueDTO> attributeValues) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.attributeId = attributeId;
        this.attributeName = attributeName;
        this.attributeValues = attributeValues;
    }

    public static ProductAttributeDTO fromEntity(ProductAttribute entity) {
        UUID prodId = entity.getProduct() != null ? entity.getProduct().getId() : null;
        String prodName = entity.getProduct() != null ? entity.getProduct().getName() : null;
        UUID attrId = entity.getAttribute() != null ? entity.getAttribute().getId() : null;
        String attrName = entity.getAttribute() != null ? entity.getAttribute().getName() : null;

        List<AttributeValueDTO> attributeValues = entity.getValues() != null
                ? entity.getValues().stream()
                .map(pav -> AttributeValueDTO.fromEntity(pav.getAttributeValue()))
                .collect(Collectors.toList())
                : Collections.emptyList();

        return new ProductAttributeDTO(
                entity.getId(), prodId, prodName,
                attrId, attrName,
                attributeValues
        );
    }
}
