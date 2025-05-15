package com.ecommerce.Ecomerce.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipping_zones")
public class ShippingZone {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String displayName;
    private boolean active = false;
    private boolean freeShipping = false;
    private String rateType;
    @Column(nullable = false)
    private Date createdAt = new Date();
    @Column(nullable = false)
    private Date updatedAt = new Date();
}
