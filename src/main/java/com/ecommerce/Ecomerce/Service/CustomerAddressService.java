package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.CustomerAddress;

import java.util.List;
import java.util.UUID;

public interface CustomerAddressService {
    List<CustomerAddress> getAll();
    CustomerAddress getById(UUID id);
    CustomerAddress create(CustomerAddress address);
    CustomerAddress update(UUID id, CustomerAddress address);
    void delete(UUID id);
}
