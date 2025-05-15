package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Entity.ProductAttributeValue;
import com.ecommerce.Ecomerce.Service.ProductAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/product-attribute-values")
public class ProductAttributeValueController {

    @Autowired
    private ProductAttributeValueService service;

    @GetMapping
    public List<ProductAttributeValue> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAttributeValue> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductAttributeValue> create(@RequestBody ProductAttributeValue pav) {
        return ResponseEntity.ok(service.create(pav));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductAttributeValue> update(@PathVariable UUID id, @RequestBody ProductAttributeValue pav) {
        return ResponseEntity.ok(service.update(id, pav));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
