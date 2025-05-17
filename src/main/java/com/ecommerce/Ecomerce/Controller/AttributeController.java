package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Entity.Attribute;
import com.ecommerce.Ecomerce.Service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin
@RestController
@RequestMapping("/api/attributes")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @GetMapping
    public List<Attribute> getAll() {
        return attributeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attribute> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(attributeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Attribute> create(@RequestBody Attribute attribute) {
        return ResponseEntity.ok(attributeService.create(attribute));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attribute> update(@PathVariable UUID id, @RequestBody Attribute attribute) {
        return ResponseEntity.ok(attributeService.update(id, attribute));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        attributeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
