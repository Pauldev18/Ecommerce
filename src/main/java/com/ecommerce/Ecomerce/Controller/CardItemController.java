package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Entity.CardItem;
import com.ecommerce.Ecomerce.Service.CardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/card-items")
public class CardItemController {

    @Autowired
    private CardItemService cardItemService;

    @GetMapping
    public List<CardItem> getAll() {
        return cardItemService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardItem> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(cardItemService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CardItem> create(@RequestBody CardItem cardItem) {
        return ResponseEntity.ok(cardItemService.create(cardItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardItem> update(@PathVariable UUID id, @RequestBody CardItem cardItem) {
        return ResponseEntity.ok(cardItemService.update(id, cardItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cardItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
