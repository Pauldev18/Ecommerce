package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Entity.CustomerAddress;
import com.ecommerce.Ecomerce.Service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin
@RestController
@RequestMapping("/api/customer-addresses")
public class CustomerAddressController {

    @Autowired
    private CustomerAddressService service;

    @GetMapping
    public List<CustomerAddress> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddress> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerAddress> create(@RequestBody CustomerAddress address) {
        return ResponseEntity.ok(service.create(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddress> update(@PathVariable UUID id,
                                                  @RequestBody CustomerAddress address) {
        return ResponseEntity.ok(service.update(id, address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
