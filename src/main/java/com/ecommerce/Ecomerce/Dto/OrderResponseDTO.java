package com.ecommerce.Ecomerce.Dto;
import lombok.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private String id;
    private UUID customerId;
    private UUID couponId;
    private UUID statusId;
    private String statusName;
    private Date createdAt;
    private Date orderApprovedAt;
    private Date orderDeliveredCarrierDate;
    private Date orderDeliveredCustomerDate;
    private List<OrderItemDTO> items;
    private Double totalPrice;
}
