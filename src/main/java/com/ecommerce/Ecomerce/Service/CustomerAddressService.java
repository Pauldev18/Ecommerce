package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.CustomerAddress;

import java.util.List;
import java.util.UUID;

public interface CustomerAddressService {
    CustomerAddress getByCustomerId(UUID customerId);
    CustomerAddress createOrUpdateAddress(CustomerAddress address);
}
