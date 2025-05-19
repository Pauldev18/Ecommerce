package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.ProductAttributeDTO;
import com.ecommerce.Ecomerce.Entity.ProductAttribute;
import com.ecommerce.Ecomerce.Service.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/product-attributes")
public class ProductAttributeController {

    @Autowired
    private ProductAttributeService productAttributeService;

    @GetMapping
    public List<ProductAttributeDTO> getAll() {
        return productAttributeService.getAll().stream()
                .map(ProductAttributeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAttributeDTO> getById(@PathVariable UUID id) {
        ProductAttribute entity = productAttributeService.getById(id);
        return ResponseEntity.ok(ProductAttributeDTO.fromEntity(entity));
    }

    @PostMapping
    public ResponseEntity<ProductAttributeDTO> create(@RequestBody ProductAttribute entity) {
        ProductAttribute created = productAttributeService.create(entity);
        return ResponseEntity.ok(ProductAttributeDTO.fromEntity(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductAttributeDTO> update(
            @PathVariable UUID id,
            @RequestBody ProductAttribute entity) {
        ProductAttribute updated = productAttributeService.update(id, entity);
        return ResponseEntity.ok(ProductAttributeDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productAttributeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
