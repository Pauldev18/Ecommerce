package com.ecommerce.Ecomerce.Dto;

import com.ecommerce.Ecomerce.Entity.Product;
import com.ecommerce.Ecomerce.Entity.ProductGallery;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ProductGalleryDTO {
    private UUID id;
    private UUID productId;
    private String image;
    private String placeholder;
    private boolean isThumbnail;
    private Date createdAt;
    private Date updatedAt;

    public static ProductGalleryDTO fromEntity(ProductGallery entity) {
        ProductGalleryDTO dto = new ProductGalleryDTO();
        dto.setId(entity.getId());
        dto.setProductId(entity.getProduct() != null ? entity.getProduct().getId() : null);
        dto.setImage(entity.getImage());
        dto.setPlaceholder(entity.getPlaceholder());
        dto.setThumbnail(entity.isThumbnail());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public static ProductGallery toEntity(ProductGalleryDTO dto) {
        ProductGallery entity = new ProductGallery();
        entity.setId(dto.getId());
        Product product = new Product();
        product.setId(dto.getProductId());
        entity.setProduct(product);
        entity.setImage(dto.getImage());
        entity.setPlaceholder(dto.getPlaceholder());
        entity.setThumbnail(dto.isThumbnail());
        entity.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : new Date());
        entity.setUpdatedAt(new Date());
        return entity;
    }
}