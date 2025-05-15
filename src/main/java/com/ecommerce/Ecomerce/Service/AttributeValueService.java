package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.AttributeValue;

import java.util.List;
import java.util.UUID;

public interface AttributeValueService {
    List<AttributeValue> getAll();
    AttributeValue getById(UUID id);
    AttributeValue create(AttributeValue attributeValue);
    AttributeValue update(UUID id, AttributeValue attributeValue);
    void delete(UUID id);
}
