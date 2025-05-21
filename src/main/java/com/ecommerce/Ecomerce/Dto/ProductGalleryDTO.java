package com.ecommerce.Ecomerce.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductGalleryDTO {
    private UUID id;
    private String image;
    private boolean thumbnail;
}