package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.ProductAttributeValue;

import java.util.List;
import java.util.UUID;

public interface ProductAttributeValueService {
    List<ProductAttributeValue> getAll();
    ProductAttributeValue getById(UUID id);
    ProductAttributeValue create(ProductAttributeValue pav);
    ProductAttributeValue update(UUID id, ProductAttributeValue pav);
    void delete(UUID id);
}
