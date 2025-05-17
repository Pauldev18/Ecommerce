package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<Order> getAll();
    Order getById(String id);
    Order create(Order order);
    Order update(String id, Order order);
    void delete(String id);
}
