package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    void deleteByOrderId(String orderId);
}