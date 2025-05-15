package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, UUID> {
}