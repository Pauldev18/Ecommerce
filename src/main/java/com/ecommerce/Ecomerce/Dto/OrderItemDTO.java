package com.ecommerce.Ecomerce.Dto;

import lombok.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private UUID productId;
    private Double price;
    private Integer quantity;
    private String image;
}