package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.OrderItem;
import com.ecommerce.Ecomerce.Repository.OrderItemRepository;
import com.ecommerce.Ecomerce.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    @Override
    public List<OrderItem> getAll() {
        return repository.findAll();
    }

    @Override
    public OrderItem getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy OrderItem với ID: " + id));
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    @Override
    public OrderItem update(UUID id, OrderItem newItem) {
        OrderItem existing = getById(id);
        existing.setProduct(newItem.getProduct());
        existing.setOrder(newItem.getOrder());
        existing.setPrice(newItem.getPrice());
        existing.setQuantity(newItem.getQuantity());
        return repository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
