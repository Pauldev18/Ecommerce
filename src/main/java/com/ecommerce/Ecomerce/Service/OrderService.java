package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Dto.OrderRequestDTO;
import com.ecommerce.Ecomerce.Dto.OrderResponseDTO;
import com.ecommerce.Ecomerce.Entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> findAll();
    List<OrderResponseDTO> findByCustomerId(String customerId);
    OrderResponseDTO findById(String id);
    OrderResponseDTO createOrder(OrderRequestDTO request);
    OrderResponseDTO updateOrder(String id, OrderRequestDTO request);
    void deleteOrder(String id);
}
