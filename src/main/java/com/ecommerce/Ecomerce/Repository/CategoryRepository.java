package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByParentIsNull();
}