package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Slideshow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlideshowRepository extends JpaRepository<Slideshow, UUID> {
}