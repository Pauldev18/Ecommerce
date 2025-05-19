package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.AttributeValueDTO;
import com.ecommerce.Ecomerce.Entity.AttributeValue;
import com.ecommerce.Ecomerce.Service.AttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/attribute-values")
public class AttributeValueController {

    @Autowired
    private AttributeValueService attributeValueService;

    @GetMapping
    public List<AttributeValueDTO> getAll() {
        List<AttributeValue> list = attributeValueService.getAll();
        return list.stream()
                .map(AttributeValueDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeValueDTO> getById(@PathVariable UUID id) {
        AttributeValue entity = attributeValueService.getById(id);
        return ResponseEntity.ok(AttributeValueDTO.fromEntity(entity));
    }

    @PostMapping
    public ResponseEntity<AttributeValueDTO> create(@RequestBody AttributeValue attributeValue) {
        AttributeValue created = attributeValueService.create(attributeValue);
        return ResponseEntity.ok(AttributeValueDTO.fromEntity(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeValueDTO> update(
            @PathVariable UUID id,
            @RequestBody AttributeValue attributeValue) {
        AttributeValue updated = attributeValueService.update(id, attributeValue);
        return ResponseEntity.ok(AttributeValueDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        attributeValueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
