package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.Order;
import com.ecommerce.Ecomerce.Repository.OrderRepository;
import com.ecommerce.Ecomerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy Order với ID: " + id));
    }

    @Override
    public Order create(Order order) {
        // nếu cần tự tạo ID theo format riêng, gán order.setId(...)
        order.setCreatedAt(new Date());
        return orderRepository.save(order);
    }

    @Override
    public Order update(String id, Order newOrder) {
        Order existing = getById(id);
        existing.setCoupon(newOrder.getCoupon());
        existing.setCustomer(newOrder.getCustomer());
        existing.setStatus(newOrder.getStatus());
        existing.setOrderApprovedAt(newOrder.getOrderApprovedAt());
        existing.setOrderDeliveredCarrierDate(newOrder.getOrderDeliveredCarrierDate());
        existing.setOrderDeliveredCustomerDate(newOrder.getOrderDeliveredCustomerDate());
        return orderRepository.save(existing);
    }

    @Override
    public void delete(String id) {
        orderRepository.deleteById(id);
    }
}
