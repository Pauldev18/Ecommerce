package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.CartItemRequestDTO;
import com.ecommerce.Ecomerce.Dto.CartResponseDTO;
import com.ecommerce.Ecomerce.Entity.Card;
import com.ecommerce.Ecomerce.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin
@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cartService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable UUID customerId) {
        CartResponseDTO cart = cartService.getCartByCustomer(customerId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{customerId}/items")
    public ResponseEntity<CartResponseDTO> addItem(@PathVariable UUID customerId,
                                                   @RequestBody CartItemRequestDTO request) {
        CartResponseDTO updated = cartService.addItem(customerId, request);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{customerId}/items")
    public ResponseEntity<CartResponseDTO> updateItem(@PathVariable UUID customerId,
                                                      @RequestBody CartItemRequestDTO request) {
        CartResponseDTO updated = cartService.updateItem(customerId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{customerId}/items/{productId}")
    public ResponseEntity<CartResponseDTO> removeItem(@PathVariable UUID customerId,
                                                      @PathVariable UUID productId) {
        CartResponseDTO updated = cartService.removeItem(customerId, productId);
        return ResponseEntity.ok(updated);
    }
}
