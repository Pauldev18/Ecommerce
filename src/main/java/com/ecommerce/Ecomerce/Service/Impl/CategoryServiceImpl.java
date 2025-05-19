package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.Category;
import com.ecommerce.Ecomerce.Repository.CategoryRepository;
import com.ecommerce.Ecomerce.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {return categoryRepository.findByParentIsNull();
    }

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + id));
    }

    @Override
    public Category createCategory(Category category) {
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(UUID id, Category updatedCategory) {
        Category existing = getCategoryById(id);
        existing.setCategoryName(updatedCategory.getCategoryName());
        existing.setDescription(updatedCategory.getDescription());
        existing.setIcon(updatedCategory.getIcon());
        existing.setImage(updatedCategory.getImage());
        existing.setPlaceholder(updatedCategory.getPlaceholder());
        existing.setParent(updatedCategory.getParent());
        existing.setActive(updatedCategory.isActive());
        existing.setUpdatedAt(new Date());
        return categoryRepository.save(existing);
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
