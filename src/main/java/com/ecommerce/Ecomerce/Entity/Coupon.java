package com.ecommerce.Ecomerce.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code;
    private Double discountValue;
    private String discountType;
    private Integer timesUsed = 0;
    private Integer maxUsage;
    private Double orderAmountLimit;
    private Date couponStartDate;
    private Date couponEndDate;
}