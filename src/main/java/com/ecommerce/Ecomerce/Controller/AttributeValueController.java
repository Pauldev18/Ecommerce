package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Entity.AttributeValue;
import com.ecommerce.Ecomerce.Service.AttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/attribute-values")
public class AttributeValueController {

    @Autowired
    private AttributeValueService attributeValueService;

    @GetMapping
    public List<AttributeValue> getAll() {
        return attributeValueService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeValue> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(attributeValueService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AttributeValue> create(@RequestBody AttributeValue attributeValue) {
        return ResponseEntity.ok(attributeValueService.create(attributeValue));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeValue> update(@PathVariable UUID id, @RequestBody AttributeValue attributeValue) {
        return ResponseEntity.ok(attributeValueService.update(id, attributeValue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        attributeValueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
