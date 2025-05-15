package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.Attribute;
import com.ecommerce.Ecomerce.Repository.AttributeRepository;
import com.ecommerce.Ecomerce.Service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public List<Attribute> getAll() {
        return attributeRepository.findAll();
    }

    @Override
    public Attribute getById(UUID id) {
        return attributeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy Attribute với ID: " + id));
    }

    @Override
    public Attribute create(Attribute attribute) {
        attribute.setCreatedAt(new Date());
        attribute.setUpdatedAt(new Date());
        return attributeRepository.save(attribute);
    }

    @Override
    public Attribute update(UUID id, Attribute attribute) {
        Attribute existing = getById(id);
        existing.setName(attribute.getName());
        existing.setUpdatedAt(new Date());
        existing.setUpdatedBy(attribute.getUpdatedBy());
        return attributeRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        attributeRepository.deleteById(id);
    }
}
