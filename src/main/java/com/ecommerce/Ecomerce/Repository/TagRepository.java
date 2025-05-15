package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
}