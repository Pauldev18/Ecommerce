package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, UUID> {
}