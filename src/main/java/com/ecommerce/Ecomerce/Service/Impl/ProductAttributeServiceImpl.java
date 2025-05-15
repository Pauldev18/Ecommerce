package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.ProductAttribute;
import com.ecommerce.Ecomerce.Repository.ProductAttributeRepository;
import com.ecommerce.Ecomerce.Service.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {

    @Autowired
    private ProductAttributeRepository productAttributeRepository;

    @Override
    public List<ProductAttribute> getAll() {
        return productAttributeRepository.findAll();
    }

    @Override
    public ProductAttribute getById(UUID id) {
        return productAttributeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy ProductAttribute với ID: " + id));
    }

    @Override
    public ProductAttribute create(ProductAttribute productAttribute) {
        return productAttributeRepository.save(productAttribute);
    }

    @Override
    public ProductAttribute update(UUID id, ProductAttribute newItem) {
        ProductAttribute existing = getById(id);
        existing.setProduct(newItem.getProduct());
        existing.setAttribute(newItem.getAttribute());
        return productAttributeRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        productAttributeRepository.deleteById(id);
    }
}
