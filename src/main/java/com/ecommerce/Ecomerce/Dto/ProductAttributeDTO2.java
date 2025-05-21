package com.ecommerce.Ecomerce.Dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class ProductAttributeDTO2 {
    private UUID id;
    private String name;
    private List<AttributeValueDTO> values;
}
