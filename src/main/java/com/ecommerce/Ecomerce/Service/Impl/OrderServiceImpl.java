package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Dto.OrderItemDTO;
import com.ecommerce.Ecomerce.Dto.OrderRequestDTO;
import com.ecommerce.Ecomerce.Dto.OrderResponseDTO;
import com.ecommerce.Ecomerce.Entity.Customer;
import com.ecommerce.Ecomerce.Entity.Order;
import com.ecommerce.Ecomerce.Entity.OrderItem;
import com.ecommerce.Ecomerce.Entity.Product;
import com.ecommerce.Ecomerce.Repository.*;
import com.ecommerce.Ecomerce.Service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CouponRepository couponRepository;
    private final OrderStatusRepository statusRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            CouponRepository couponRepository,
                            OrderStatusRepository statusRepository,
                            CustomerRepository customerRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.couponRepository = couponRepository;
        this.statusRepository = statusRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    private OrderResponseDTO mapToDTO(Order order) {
        List<OrderItemDTO> items = order.getOrderItems().stream()
                .map(i -> new OrderItemDTO(i.getProduct().getId(), i.getPrice(), i.getQuantity()))
                .collect(Collectors.toList());
        return new OrderResponseDTO(
                order.getId(),
                order.getCustomer().getId(),
                order.getCoupon() != null ? order.getCoupon().getId() : null,
                order.getStatus().getId(),
                order.getStatus().getName(),
                order.getCreatedAt(),
                order.getOrderApprovedAt(),
                order.getOrderDeliveredCarrierDate(),
                order.getOrderDeliveredCustomerDate(),
                items
        );
    }

    @Override
    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDTO> findByCustomerId(UUID customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO findById(String id) {
        return orderRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
        order.setCustomer(customer);
        if (request.getCouponId() != null) {
            order.setCoupon(couponRepository.findById(request.getCouponId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid coupon ID")));
        }
        order.setStatus(statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid status ID")));
        order.setOrderApprovedAt(request.getOrderApprovedAt());
        order.setOrderDeliveredCarrierDate(request.getOrderDeliveredCarrierDate());
        order.setOrderDeliveredCustomerDate(request.getOrderDeliveredCustomerDate());
        order.setCreatedAt(new Date());
        Order saved = orderRepository.save(order);

        List<OrderItem> items = request.getItems().stream().map(dto -> {
            OrderItem item = new OrderItem();
            item.setOrder(saved);
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
            item.setProduct(product);
            item.setPrice(dto.getPrice());
            item.setQuantity(dto.getQuantity());
            return orderItemRepository.save(item);
        }).collect(Collectors.toList());
        saved.setOrderItems(items);
        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrder(String id, OrderRequestDTO request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
        // update fields similar to create
        if (request.getCouponId() != null) {
            order.setCoupon(couponRepository.findById(request.getCouponId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid coupon ID")));
        } else {
            order.setCoupon(null);
        }
        order.setStatus(statusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid status ID")));
        order.setOrderApprovedAt(request.getOrderApprovedAt());
        order.setOrderDeliveredCarrierDate(request.getOrderDeliveredCarrierDate());
        order.setOrderDeliveredCustomerDate(request.getOrderDeliveredCustomerDate());
        // clear and save items
        orderItemRepository.deleteByOrderId(id);
        List<OrderItem> items = request.getItems().stream().map(dto -> {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
            item.setProduct(product);
            item.setPrice(dto.getPrice());
            item.setQuantity(dto.getQuantity());
            return orderItemRepository.save(item);
        }).collect(Collectors.toList());
        order.setOrderItems(items);
        Order updated = orderRepository.save(order);
        return mapToDTO(updated);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
