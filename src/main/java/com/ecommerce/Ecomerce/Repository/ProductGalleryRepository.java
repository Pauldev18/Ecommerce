package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.ProductGallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductGalleryRepository extends JpaRepository<ProductGallery, UUID> {
    List<ProductGallery> findByProductId(UUID productId);
}