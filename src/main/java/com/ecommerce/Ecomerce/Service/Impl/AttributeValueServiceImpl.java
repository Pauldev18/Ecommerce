package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.AttributeValue;
import com.ecommerce.Ecomerce.Repository.AttributeValueRepository;
import com.ecommerce.Ecomerce.Service.AttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttributeValueServiceImpl implements AttributeValueService {

    @Autowired
    private AttributeValueRepository attributeValueRepository;

    @Override
    public List<AttributeValue> getAll() {
        return attributeValueRepository.findAll();
    }

    @Override
    public AttributeValue getById(UUID id) {
        return attributeValueRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy AttributeValue với ID: " + id));
    }

    @Override
    public AttributeValue create(AttributeValue attributeValue) {
        return attributeValueRepository.save(attributeValue);
    }

    @Override
    public AttributeValue update(UUID id, AttributeValue attributeValue) {
        AttributeValue existing = getById(id);
        existing.setAttribute(attributeValue.getAttribute());
        existing.setValue(attributeValue.getValue());
        existing.setColor(attributeValue.getColor());
        return attributeValueRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        attributeValueRepository.deleteById(id);
    }
}
