package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.ProductCategory;
import com.ecommerce.Ecomerce.Repository.ProductCategoryRepository;
import com.ecommerce.Ecomerce.Service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> getAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getById(UUID id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy ProductCategory với ID: " + id));
    }

    @Override
    public ProductCategory create(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory update(UUID id, ProductCategory newPC) {
        ProductCategory existing = getById(id);
        existing.setProduct(newPC.getProduct());
        existing.setCategory(newPC.getCategory());
        return productCategoryRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        productCategoryRepository.deleteById(id);
    }
}
