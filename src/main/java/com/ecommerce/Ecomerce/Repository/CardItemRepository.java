package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.CardItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardItemRepository extends JpaRepository<CardItem, UUID> {
    void deleteByCardIdAndProductId(UUID cardId, UUID productId);
    List<CardItem> findByCardCustomerId(UUID customerId);
}