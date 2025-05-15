package com.ecommerce.Ecomerce.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_shipping_info")
public class ProductShippingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Double weight;
    @Column(length = 10)
    private String weightUnit;

    private Double volume;
    @Column(length = 10)
    private String volumeUnit;

    private Double dimensionWidth;
    private Double dimensionHeight;
    private Double dimensionDepth;
    @Column(length = 10)
    private String dimensionUnit;
}