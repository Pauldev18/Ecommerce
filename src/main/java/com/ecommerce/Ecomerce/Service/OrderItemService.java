package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {
    List<OrderItem> getAll();
    OrderItem getById(UUID id);
    OrderItem create(OrderItem orderItem);
    OrderItem update(UUID id, OrderItem orderItem);
    void delete(UUID id);
}
