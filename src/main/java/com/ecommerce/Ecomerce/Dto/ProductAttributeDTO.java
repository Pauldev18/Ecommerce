package com.ecommerce.Ecomerce.Dto;

import lombok.Data;

import java.util.UUID;
@Data
public class ProductAttributeDTO {
    private UUID id;
    private UUID productId;
    private String productName;
    private UUID attributeId;
    private String attributeName;

    public ProductAttributeDTO() {}

    public ProductAttributeDTO(UUID id, UUID productId, String productName,
                               UUID attributeId, String attributeName) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.attributeId = attributeId;
        this.attributeName = attributeName;
    }

    // getters & setters omitted for brevity

    public static ProductAttributeDTO fromEntity(com.ecommerce.Ecomerce.Entity.ProductAttribute entity) {
        UUID prodId = entity.getProduct() != null ? entity.getProduct().getId() : null;
        String prodName = entity.getProduct() != null ? entity.getProduct().getName() : null;
        UUID attrId = entity.getAttribute() != null ? entity.getAttribute().getId() : null;
        String attrName = entity.getAttribute() != null ? entity.getAttribute().getName() : null;
        return new ProductAttributeDTO(
                entity.getId(), prodId, prodName,
                attrId, attrName
        );
    }
}
