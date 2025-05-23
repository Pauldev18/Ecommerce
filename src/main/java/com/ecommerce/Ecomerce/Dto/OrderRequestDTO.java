package com.ecommerce.Ecomerce.Dto;
import lombok.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private UUID customerId;
    private String couponCode;
    private UUID statusId;
    private Date orderApprovedAt;
    private Date orderDeliveredCarrierDate;
    private Date orderDeliveredCustomerDate;
    private List<OrderItemDTO> items;
}