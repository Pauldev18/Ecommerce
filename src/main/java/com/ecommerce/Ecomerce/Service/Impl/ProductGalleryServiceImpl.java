package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Dto.ProductGalleryDTO;
import com.ecommerce.Ecomerce.Entity.ProductGallery;
import com.ecommerce.Ecomerce.Repository.ProductGalleryRepository;
import com.ecommerce.Ecomerce.Service.ProductGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductGalleryServiceImpl implements ProductGalleryService {

    @Autowired
    private ProductGalleryRepository galleryRepo;

    @Override
    public List<ProductGalleryDTO> getByProductId(UUID productId) {
        return galleryRepo.findByProductId(productId)
                .stream().map(ProductGalleryDTO::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductGalleryDTO> getAll() {
        return galleryRepo.findAll()
                .stream()
                .map(ProductGalleryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProductGalleryDTO getById(UUID id) {
        return galleryRepo.findById(id)
                .map(ProductGalleryDTO::fromEntity)
                .orElse(null);
    }

    @Override
    public ProductGalleryDTO create(ProductGalleryDTO dto) {
        ProductGallery gallery = ProductGalleryDTO.toEntity(dto);
        gallery.setCreatedAt(new Date());
        gallery.setUpdatedAt(new Date());
        return ProductGalleryDTO.fromEntity(galleryRepo.save(gallery));
    }

    @Override
    public ProductGalleryDTO update(UUID id, ProductGalleryDTO dto) {
        Optional<ProductGallery> existing = galleryRepo.findById(id);
        if (existing.isPresent()) {
            ProductGallery gallery = existing.get();
            gallery.setImage(dto.getImage());
            gallery.setPlaceholder(dto.getPlaceholder());
            gallery.setThumbnail(dto.isThumbnail());
            gallery.setUpdatedAt(new Date());
            return ProductGalleryDTO.fromEntity(galleryRepo.save(gallery));
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        galleryRepo.deleteById(id);
    }
}
