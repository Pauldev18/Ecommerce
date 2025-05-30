package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Dto.OrderDatesUpdateDTO;
import com.ecommerce.Ecomerce.Dto.OrderRequestDTO;
import com.ecommerce.Ecomerce.Dto.OrderResponseDTO;
import com.ecommerce.Ecomerce.Entity.Order;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderResponseDTO> findAll();
    List<OrderResponseDTO> findByCustomerId(UUID customerId);


    OrderResponseDTO findById(String id);
    OrderResponseDTO createOrder(OrderRequestDTO request);

    void deleteOrder(String id);
     void updateOrderStatus(String orderId, UUID statusId);
    void updateOrderDates(String orderId, OrderDatesUpdateDTO dto);
    Double applyVoucher(String couponCode, Double orderTotal);
}
