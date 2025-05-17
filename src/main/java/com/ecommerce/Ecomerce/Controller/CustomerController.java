package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Entity.Customer;
import com.ecommerce.Ecomerce.Service.CustomerService;
import com.ecommerce.Ecomerce.Dto.AuthRequest;
import com.ecommerce.Ecomerce.Dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Customer customer) {
        AuthResponse res = service.register(customer);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        AuthResponse res = service.login(req);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Customer> profile(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getProfile(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable UUID id, @RequestBody Customer c) {
        return ResponseEntity.ok(service.update(id, c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
