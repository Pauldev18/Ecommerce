package com.ecommerce.Ecomerce.Dto;

import lombok.Data;

import java.util.UUID;
@Data
public class AttributeValueDTO {
    private UUID id;
    private UUID attributeId;
    private String attributeName;
    private String value;
    private String color;

    public AttributeValueDTO() {}

    public AttributeValueDTO(UUID id, UUID attributeId, String attributeName, String value, String color) {
        this.id = id;
        this.attributeId = attributeId;
        this.attributeName = attributeName;
        this.value = value;
        this.color = color;
    }



    // Chuyển từ Entity sang DTO
    public static AttributeValueDTO fromEntity(com.ecommerce.Ecomerce.Entity.AttributeValue entity) {
        UUID attrId = entity.getAttribute() != null ? entity.getAttribute().getId() : null;
        String attrName = entity.getAttribute() != null ? entity.getAttribute().getName() : null;
        return new AttributeValueDTO(
                entity.getId(),
                attrId,
                attrName,
                entity.getValue(),
                entity.getColor()
        );
    }
}
