package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.*;
import com.ecommerce.Ecomerce.Entity.Product;
import com.ecommerce.Ecomerce.Service.ProductService;
import org.springframework.beans.BeanUtils;
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
        dto.setCreatedBy(p.getCreatedBy() != null ? p.getCreatedBy().getId() : null);
        dto.setUpdatedBy(p.getUpdatedBy() != null ? p.getUpdatedBy().getId() : null);


        dto.setProductAttributes(
                p.getProductAttributes() != null
                        ? p.getProductAttributes().stream()
                        .map(ProductAttributeDTO::fromEntity)
                        .collect(Collectors.toList())
                        : Collections.emptyList()
        );


        dto.setGalleries(
                p.getGalleries() != null
                        ? p.getGalleries().stream()
                        .map(ProductGalleryDTO::fromEntity)
                        .collect(Collectors.toList())
                        : Collections.emptyList()
        );

        return dto;
    }
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(ProductController::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ProductController.toDTO(productService.getProductById(id)));
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable UUID categoryId) {
        List<Product> products = productService.getProductsByCategory(categoryId);

        List<ProductDTO> dtos = products.stream()
                .map(ProductController::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/new")
    public ResponseEntity<List<ProductDTO>> getNewProducts(
            @RequestParam(defaultValue = "10") int top) {
        List<Product> list = productService.getNewProducts(top);
        List<ProductDTO> dtos = list.stream()
                .map(ProductController::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/trending/7days")
    public ResponseEntity<List<ProductDTO>> trending7Days(
            @RequestParam(defaultValue = "done") String status,
            @RequestParam(defaultValue = "10")   int top) {

        List<Product> products =
                productService.getTrendingProductsLast7Days(status, top);
        List<ProductDTO> dtos = products.stream()
                .map(ProductController::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/bestsellers")
    public ResponseEntity<List<ProductDTO>> getBestSellers(
            @RequestParam(defaultValue = "done") String status) {
        List<Product> products = productService.getBestSellerProducts(status);
        List<ProductDTO> dtos = products.stream()
                .map(ProductController::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
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
