package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.Attribute;

import java.util.List;
import java.util.UUID;

public interface AttributeService {
    List<Attribute> getAll();
    Attribute getById(UUID id);
    Attribute create(Attribute attribute);
    Attribute update(UUID id, Attribute attribute);
    void delete(UUID id);
}
