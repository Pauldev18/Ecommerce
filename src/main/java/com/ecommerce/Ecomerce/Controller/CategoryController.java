package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.CategoryDTO;
import com.ecommerce.Ecomerce.Entity.Category;
import com.ecommerce.Ecomerce.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // GET tất cả và trả về DTO
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        List<Category> entities = categoryService.getAllCategories();
        return entities.stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // GET theo id và trả về DTO
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable UUID id) {
        Category entity = categoryService.getCategoryById(id);
        CategoryDTO dto = CategoryDTO.fromEntity(entity);
        return ResponseEntity.ok(dto);
    }

    // Các POST, PUT, DELETE giữ nguyên (trả về entity hoặc bạn cũng có thể chuyển sang DTO tương tự)
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody Category category) {
        Category created = categoryService.createCategory(category);
        return ResponseEntity.ok(CategoryDTO.fromEntity(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable UUID id, @RequestBody Category category) {
        Category updated = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(CategoryDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
