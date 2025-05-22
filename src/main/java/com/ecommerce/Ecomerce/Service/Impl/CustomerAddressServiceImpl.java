package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.CustomerAddress;
import com.ecommerce.Ecomerce.Repository.CustomerAddressRepository;
import com.ecommerce.Ecomerce.Service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    @Autowired
    private CustomerAddressRepository repository;

    @Override
    public CustomerAddress getByCustomerId(UUID customerId) {
        List<CustomerAddress> addresses = repository.findByCustomerId(customerId);
        if (addresses.isEmpty()) {
            return null;
        }
        return addresses.get(0);
    }


    @Override
    public CustomerAddress createOrUpdateAddress(CustomerAddress address) {
        UUID customerId = address.getCustomer().getId();

        // Tìm tất cả địa chỉ của customer
        List<CustomerAddress> existingAddresses = repository.findByCustomerId(customerId);

        if (!existingAddresses.isEmpty()) {

            CustomerAddress existing = existingAddresses.get(0);

            existing.setLine1(address.getLine1());
            existing.setLine2(address.getLine2());
            existing.setPhoneNumber(address.getPhoneNumber());
            existing.setDialCode(address.getDialCode());
            existing.setCountry(address.getCountry());
            existing.setPostalCode(address.getPostalCode());
            existing.setCity(address.getCity());

            return repository.save(existing);
        } else {
            // Nếu customer chưa có địa chỉ, tạo mới
            return repository.save(address);
        }
    }

}
