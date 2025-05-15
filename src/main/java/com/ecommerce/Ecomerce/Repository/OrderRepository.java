package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}