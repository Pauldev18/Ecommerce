package com.ecommerce.Ecomerce.Dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
public class ProductDTO {
    private UUID id;
    private String slug;
    private String name;
    private String sku;
    private Double salePrice;
    private Double comparePrice;
    private Integer quantity;
    private String shortDescription;
    private String description;
    private String type;
    private boolean published;
    private boolean disableOutOfStock;
    private String note;
    private UUID createdBy;
    private UUID updatedBy;
    private Date createdAt;
    private Date updatedAt;
    private List<ProductAttributeDTO> productAttributes;
    private List<ProductGalleryDTO> galleries;

}