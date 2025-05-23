package com.ecommerce.Ecomerce.Controller;

import com.ecommerce.Ecomerce.Dto.*;
import com.ecommerce.Ecomerce.Entity.Coupon;
import com.ecommerce.Ecomerce.Entity.Order;
import com.ecommerce.Ecomerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {


    @Autowired
    private  OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByCustomer(@PathVariable UUID customerId) {
        List<OrderResponseDTO> orders = orderService.findByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable String id) {
        OrderResponseDTO order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO request) {
        OrderResponseDTO created = orderService.createOrder(request);
        return ResponseEntity.created(URI.create("/api/orders/" + created.getId()))
                .body(created);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{orderId}/status/{statusId}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable String orderId,
                                                  @PathVariable UUID statusId) {
        orderService.updateOrderStatus(orderId, statusId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{orderId}/dates")
    public ResponseEntity<Void> updateOrderDates(
            @PathVariable String orderId,
            @RequestBody OrderDatesUpdateDTO datesDto) {
        orderService.updateOrderDates(orderId, datesDto);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/applyVoucher")
    public ResponseEntity<ApplyVoucherResponse> applyVoucher(@RequestBody ApplyVoucherRequest request) {
        try {
            double discountAmount = orderService.applyVoucher(request.getCouponCode(), request.getOrderTotal());
            return ResponseEntity.ok(new ApplyVoucherResponse(true, "Mã giảm giá hợp lệ", discountAmount));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApplyVoucherResponse(false, e.getMessage(), 0.0));
        }
    }



}
