package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.ProductAttributeValue;
import com.ecommerce.Ecomerce.Repository.ProductAttributeValueRepository;
import com.ecommerce.Ecomerce.Service.ProductAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductAttributeValueServiceImpl implements ProductAttributeValueService {

    @Autowired
    private ProductAttributeValueRepository repository;

    @Override
    public List<ProductAttributeValue> getAll() {
        return repository.findAll();
    }

    @Override
    public ProductAttributeValue getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy ProductAttributeValue với ID: " + id));
    }

    @Override
    public ProductAttributeValue create(ProductAttributeValue pav) {
        return repository.save(pav);
    }

    @Override
    public ProductAttributeValue update(UUID id, ProductAttributeValue newValue) {
        ProductAttributeValue existing = getById(id);
        existing.setProductAttribute(newValue.getProductAttribute());
        existing.setAttributeValue(newValue.getAttributeValue());
        return repository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
