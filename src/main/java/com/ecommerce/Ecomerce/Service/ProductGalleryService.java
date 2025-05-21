package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Dto.ProductGalleryDTO;

import java.util.List;
import java.util.UUID;

public interface ProductGalleryService {
    List<ProductGalleryDTO> getAll();
    List<ProductGalleryDTO> getByProductId(UUID productId);
    ProductGalleryDTO getById(UUID id);
    ProductGalleryDTO create(ProductGalleryDTO dto);
    ProductGalleryDTO update(UUID id, ProductGalleryDTO dto);
    void delete(UUID id);
}
