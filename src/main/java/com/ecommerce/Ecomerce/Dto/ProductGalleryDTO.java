package com.ecommerce.Ecomerce.Dto;

import com.ecommerce.Ecomerce.Entity.ProductGallery;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductGalleryDTO {
    private UUID id;
    private String image;
    private boolean thumbnail;
    public static ProductGalleryDTO fromEntity(ProductGallery pg) {
        ProductGalleryDTO dto = new ProductGalleryDTO();
        dto.setId(pg.getId());
        dto.setImage(pg.getImage());
        dto.setThumbnail(pg.isThumbnail());
        return dto;
    }
}