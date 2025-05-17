package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Entity.ProductAttribute;
import com.ecommerce.Ecomerce.Service.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin
@RestController
@RequestMapping("/api/product-attributes")
public class ProductAttributeController {

    @Autowired
    private ProductAttributeService productAttributeService;

    @GetMapping
    public List<ProductAttribute> getAll() {
        return productAttributeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAttribute> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(productAttributeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductAttribute> create(@RequestBody ProductAttribute productAttribute) {
        return ResponseEntity.ok(productAttributeService.create(productAttribute));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductAttribute> update(@PathVariable UUID id, @RequestBody ProductAttribute productAttribute) {
        return ResponseEntity.ok(productAttributeService.update(id, productAttribute));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productAttributeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
