package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.Customer;
import com.ecommerce.Ecomerce.Dto.AuthRequest;
import com.ecommerce.Ecomerce.Dto.AuthResponse;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> getAll();
    Customer getById(UUID id);

    AuthResponse register(Customer customer);
    AuthResponse login(AuthRequest request);

    Customer getProfile(UUID id);

    Customer update(UUID id, Customer customer);
    void delete(UUID id);
}
