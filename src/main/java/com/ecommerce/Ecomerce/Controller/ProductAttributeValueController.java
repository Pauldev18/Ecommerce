package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.ProductAttributeValueReponse;
import com.ecommerce.Ecomerce.Entity.ProductAttributeValue;
import com.ecommerce.Ecomerce.Service.ProductAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/product-attribute-values")
public class ProductAttributeValueController {

    @Autowired
    private ProductAttributeValueService service;
    private ProductAttributeValueReponse toDTO(ProductAttributeValue pav) {
        ProductAttributeValueReponse dto = new ProductAttributeValueReponse();
        dto.setId(pav.getId());
        dto.setProductAttributeId(pav.getProductAttribute().getId());
        dto.setProductAttributeName(pav.getProductAttribute().getProduct().getName());

        dto.setAttributeValueId(pav.getAttributeValue().getId());
        dto.setAttributeValueName(pav.getAttributeValue().getValue());
        return dto;
    }

    @GetMapping
    public List<ProductAttributeValueReponse> getAll() {
        List<ProductAttributeValue> list = service.getAll();
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAttributeValueReponse> getById(@PathVariable UUID id) {
        ProductAttributeValue pav = service.getById(id);
        return ResponseEntity.ok(toDTO(pav));
    }

    @PostMapping
    public ResponseEntity<ProductAttributeValueReponse> create(@RequestBody ProductAttributeValue pav) {
        ProductAttributeValue saved = service.create(pav);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductAttributeValueReponse> update(@PathVariable UUID id, @RequestBody ProductAttributeValue pav) {
        ProductAttributeValue updated = service.update(id, pav);
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
