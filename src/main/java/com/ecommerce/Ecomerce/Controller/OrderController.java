package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.OrderRequestDTO;
import com.ecommerce.Ecomerce.Dto.OrderResponseDTO;
import com.ecommerce.Ecomerce.Entity.Order;
import com.ecommerce.Ecomerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {


    @Autowired
    private  OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByCustomer(@PathVariable UUID customerId) {
        List<OrderResponseDTO> orders = orderService.findByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable String id) {
        OrderResponseDTO order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO request) {
        OrderResponseDTO created = orderService.createOrder(request);
        return ResponseEntity.created(URI.create("/api/orders/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable String id,
                                                        @RequestBody OrderRequestDTO request) {
        OrderResponseDTO updated = orderService.updateOrder(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
