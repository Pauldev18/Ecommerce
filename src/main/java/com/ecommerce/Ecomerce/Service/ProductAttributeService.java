package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.ProductAttribute;

import java.util.List;
import java.util.UUID;

public interface ProductAttributeService {
    List<ProductAttribute> getAll();
    ProductAttribute getById(UUID id);
    ProductAttribute create(ProductAttribute productAttribute);
    ProductAttribute update(UUID id, ProductAttribute productAttribute);
    void delete(UUID id);
}
