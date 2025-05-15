package com.ecommerce.Ecomerce.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_suppliers")
public class ProductSupplier {
    @EmbeddedId
    private ProductSupplierId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @MapsId("supplierId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Embeddable
    @Data
    public static class ProductSupplierId implements Serializable {
        private UUID productId;
        private UUID supplierId;
    }
}
