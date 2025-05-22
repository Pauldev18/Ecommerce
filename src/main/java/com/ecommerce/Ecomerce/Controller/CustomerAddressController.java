package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.CustomerAddressDTO;
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

    public CustomerAddressDTO convertToDTO(CustomerAddress address) {
        return new CustomerAddressDTO(
                address.getId(),
                address.getCustomer().getId(),
                address.getCity(),
                address.getCountry(),
                address.getDialCode(),
                address.getLine1(),
                address.getLine2(),
                address.getPhoneNumber(),
                address.getPostalCode()
        );
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerAddressDTO> getByCustomerId(@PathVariable UUID customerId) {
        CustomerAddress data = service.getByCustomerId(customerId);
        return ResponseEntity.ok(this.convertToDTO(data));
    }

    @PostMapping
    public ResponseEntity<CustomerAddressDTO> createOrUpdate(@RequestBody CustomerAddress address) {
        CustomerAddress saved = service.createOrUpdateAddress(address);
        CustomerAddressDTO dto = this.convertToDTO(saved);
        return ResponseEntity.ok(dto);
    }
}
