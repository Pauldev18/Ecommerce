package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Dto.OrderDatesUpdateDTO;
import com.ecommerce.Ecomerce.Dto.OrderItemDTO;
import com.ecommerce.Ecomerce.Dto.OrderRequestDTO;
import com.ecommerce.Ecomerce.Dto.OrderResponseDTO;
import com.ecommerce.Ecomerce.Entity.*;
import com.ecommerce.Ecomerce.Repository.*;
import com.ecommerce.Ecomerce.Service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    private final CardItemRepository cardItemRepository;


    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            CouponRepository couponRepository,
                            OrderStatusRepository statusRepository,
                            CustomerRepository customerRepository,
                            ProductRepository productRepository,
                            CardItemRepository cardItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.couponRepository = couponRepository;
        this.statusRepository = statusRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.cardItemRepository = cardItemRepository;
    }

    private OrderResponseDTO mapToDTO(Order order) {
        List<OrderItemDTO> items = order.getOrderItems().stream()
                .map(i -> new OrderItemDTO(
                        i.getProduct().getId(),
                        i.getPrice(),
                        i.getQuantity(),
                        i.getProduct().getGalleries().get(0).getImage()))
                .collect(Collectors.toList());

        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();


        if (order.getCoupon() != null) {
            Coupon coupon = order.getCoupon();
            if ("percent".equalsIgnoreCase(coupon.getDiscountType())) {
                total -= total * (coupon.getDiscountValue() / 100);
            } else if ("fixed".equalsIgnoreCase(coupon.getDiscountType())) {
                total -= coupon.getDiscountValue();
            }


            if (total < 0) {
                total = 0;
            }
        }

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
                items,
                total
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
    @Transactional
    public void updateOrderDates(String orderId, OrderDatesUpdateDTO dto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đơn hàng với id: " + orderId));

        if (dto.getOrderApprovedAt() != null) {
            order.setOrderApprovedAt(dto.getOrderApprovedAt());
        }
        if (dto.getOrderDeliveredCarrierDate() != null) {
            order.setOrderDeliveredCarrierDate(dto.getOrderDeliveredCarrierDate());
        }
        if (dto.getOrderDeliveredCustomerDate() != null) {
            order.setOrderDeliveredCustomerDate(dto.getOrderDeliveredCustomerDate());
        }

        orderRepository.save(order);
    }

    @Override
    public Double applyVoucher(String couponCode, Double orderTotal) {
        Coupon coupon = couponRepository.findByCode(couponCode)
                .orElseThrow(() -> new IllegalArgumentException("Mã giảm giá không tồn tại"));

        Date now = new Date();
        if (coupon.getCouponStartDate() != null && now.before(coupon.getCouponStartDate())) {
            throw new IllegalArgumentException("Mã giảm giá chưa bắt đầu.");
        }
        if (coupon.getCouponEndDate() != null && now.after(coupon.getCouponEndDate())) {
            throw new IllegalArgumentException("Mã giảm giá đã hết hạn.");
        }
        if (coupon.getMaxUsage() != null && coupon.getTimesUsed() >= coupon.getMaxUsage()) {
            throw new IllegalArgumentException("Mã giảm giá đã được sử dụng tối đa.");
        }
        if (coupon.getOrderAmountLimit() != null && orderTotal < coupon.getOrderAmountLimit()) {
            throw new IllegalArgumentException("Tổng đơn hàng chưa đủ điều kiện áp dụng mã giảm giá.");
        }

        double discountAmount = 0;
        if ("percent".equalsIgnoreCase(coupon.getDiscountType())) {
            discountAmount = orderTotal * (coupon.getDiscountValue() / 100);
        } else if ("fixed".equalsIgnoreCase(coupon.getDiscountType())) {
            discountAmount = coupon.getDiscountValue();
        }

        discountAmount = Math.min(discountAmount, orderTotal);
        return discountAmount;
    }




    @Override
    public OrderResponseDTO findById(String id) {
        return orderRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }
    @Override
    @Transactional
    public void updateOrderStatus(String orderId, UUID statusId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đơn hàng với id: " + orderId));


        OrderStatus newStatus = statusRepository.findById(statusId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy trạng thái với id: " + statusId));


        order.setStatus(newStatus);
        orderRepository.save(order);
    }
    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
        order.setCustomer(customer);


        if (request.getCouponCode() != null && !request.getCouponCode().isEmpty()) {
            Coupon coupon = couponRepository.findByCode(request.getCouponCode())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid coupon code"));


            Integer usedTimes = coupon.getTimesUsed() != null ? coupon.getTimesUsed() : 0;
            coupon.setTimesUsed(usedTimes + 1);
            couponRepository.save(coupon);

            order.setCoupon(coupon);
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

        List<CardItem> cartItems = cardItemRepository.findByCardCustomerId(request.getCustomerId());
        cardItemRepository.deleteAll(cartItems);

        return mapToDTO(saved);
    }




    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
