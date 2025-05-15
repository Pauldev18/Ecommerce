package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryService {
    List<ProductCategory> getAll();
    ProductCategory getById(UUID id);
    ProductCategory create(ProductCategory productCategory);
    ProductCategory update(UUID id, ProductCategory productCategory);
    void delete(UUID id);
}
