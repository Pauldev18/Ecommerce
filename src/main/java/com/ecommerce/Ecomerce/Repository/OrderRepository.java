package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByCustomerId(UUID customerId);
}