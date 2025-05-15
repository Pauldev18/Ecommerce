package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
}