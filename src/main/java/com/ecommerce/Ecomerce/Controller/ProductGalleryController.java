package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.ProductGalleryDTO;
import com.ecommerce.Ecomerce.Service.ProductGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/galleries")
public class ProductGalleryController {

    @Autowired
    private ProductGalleryService galleryService;
    @GetMapping
    public List<ProductGalleryDTO> getAll() {
        return galleryService.getAll();
    }

    @GetMapping("/product/{productId}")
    public List<ProductGalleryDTO> getByProduct(@PathVariable UUID productId) {
        return galleryService.getByProductId(productId);
    }

    @GetMapping("/{id}")
    public ProductGalleryDTO getById(@PathVariable UUID id) {
        return galleryService.getById(id);
    }

    @PostMapping
    public ProductGalleryDTO create(@RequestBody ProductGalleryDTO dto) {
        return galleryService.create(dto);
    }

    @PutMapping("/{id}")
    public ProductGalleryDTO update(@PathVariable UUID id, @RequestBody ProductGalleryDTO dto) {
        return galleryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        galleryService.delete(id);
    }
}
