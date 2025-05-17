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
    public List<CustomerAddress> getAll() {
        return repository.findAll();
    }

    @Override
    public CustomerAddress getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy CustomerAddress với ID: " + id));
    }

    @Override
    public CustomerAddress create(CustomerAddress address) {
        return repository.save(address);
    }

    @Override
    public CustomerAddress update(UUID id, CustomerAddress newAddress) {
        CustomerAddress existing = getById(id);
        existing.setCustomer(newAddress.getCustomer());
        existing.setLine1(newAddress.getLine1());
        existing.setLine2(newAddress.getLine2());
        existing.setPhoneNumber(newAddress.getPhoneNumber());
        existing.setDialCode(newAddress.getDialCode());
        existing.setCountry(newAddress.getCountry());
        existing.setPostalCode(newAddress.getPostalCode());
        existing.setCity(newAddress.getCity());
        return repository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
