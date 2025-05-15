package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttributeRepository extends JpaRepository<Attribute, UUID> {
}