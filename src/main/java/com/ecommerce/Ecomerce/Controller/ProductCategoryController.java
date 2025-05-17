package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Entity.ProductCategory;
import com.ecommerce.Ecomerce.Service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin
@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    public List<ProductCategory> getAll() {
        return productCategoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(productCategoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductCategory> create(@RequestBody ProductCategory productCategory) {
        return ResponseEntity.ok(productCategoryService.create(productCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> update(@PathVariable UUID id, @RequestBody ProductCategory productCategory) {
        return ResponseEntity.ok(productCategoryService.update(id, productCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
