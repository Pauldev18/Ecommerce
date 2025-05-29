package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.ProductCategoryDTO;
import com.ecommerce.Ecomerce.Entity.Category;
import com.ecommerce.Ecomerce.Entity.Product;
import com.ecommerce.Ecomerce.Entity.ProductCategory;
import com.ecommerce.Ecomerce.Service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;
    private ProductCategoryDTO toDto(ProductCategory pc) {
        ProductCategoryDTO dto = new ProductCategoryDTO();
        dto.setId(pc.getId());
        dto.setProductId(pc.getProduct().getId());
        dto.setCategoryId(pc.getCategory().getId());
        return dto;
    }

    @GetMapping
    public List<ProductCategoryDTO> getAll() {
        return productCategoryService.getAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> getById(@PathVariable UUID id) {
        ProductCategory pc = productCategoryService.getById(id);
        return ResponseEntity.ok(toDto(pc));
    }

    @PostMapping
    public ResponseEntity<ProductCategoryDTO> create(@RequestBody ProductCategoryDTO dto) {
        ProductCategory pc = new ProductCategory();
        pc.setProduct(new Product());
        pc.getProduct().setId(dto.getProductId());
        pc.setCategory(new Category());
        pc.getCategory().setId(dto.getCategoryId());

        ProductCategory created = productCategoryService.create(pc);
        return ResponseEntity.ok(toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> update(
            @PathVariable UUID id,
            @RequestBody ProductCategoryDTO dto) {

        ProductCategory pc = new ProductCategory();
        pc.setProduct(new Product());
        pc.getProduct().setId(dto.getProductId());
        pc.setCategory(new Category());
        pc.getCategory().setId(dto.getCategoryId());

        ProductCategory updated = productCategoryService.update(id, pc);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
