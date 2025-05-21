package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.ProductDTO;
import com.ecommerce.Ecomerce.Entity.Product;
import com.ecommerce.Ecomerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    public static ProductDTO toDTO(Product p) {
        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId());
        dto.setSlug(p.getSlug());
        dto.setName(p.getName());
        dto.setSku(p.getSku());
        dto.setSalePrice(p.getSalePrice());
        dto.setComparePrice(p.getComparePrice());
        dto.setQuantity(p.getQuantity());
        dto.setShortDescription(p.getShortDescription());
        dto.setDescription(p.getDescription());
        dto.setType(p.getType());
        dto.setPublished(p.isPublished());
        dto.setDisableOutOfStock(p.isDisableOutOfStock());
        dto.setNote(p.getNote());
        dto.setCreatedAt(p.getCreatedAt());
        dto.setUpdatedAt(p.getUpdatedAt());
        // tránh lazy proxy: chỉ lấy một trường đơn giản từ StaffAccount
        dto.setCreatedBy(p.getCreatedBy() != null ? p.getCreatedBy().getId() : null);
        dto.setUpdatedBy(p.getUpdatedBy() != null ? p.getUpdatedBy().getId() : null);
        return dto;
    }
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(ProductController::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
