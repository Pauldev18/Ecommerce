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
@Table(name = "product_tags")
public class ProductTag {
    @EmbeddedId
    private ProductTagId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @MapsId("tagId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Embeddable
    @Data
    public static class ProductTagId implements Serializable {
        private UUID productId;
        private UUID tagId;
    }
}
