package com.ecommerce.Ecomerce.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column
    private String sku;

    @Column(name = "sale_price", nullable = false)
    private Double salePrice;

    @Column(name = "compare_price")
    private Double comparePrice;

    @Column(name = "buying_price")
    private Double buyingPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "short_description", length = 165)
    private String shortDescription;

    @Column(name = "product_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "product_type")
    private String type;

    @Column
    private boolean published;

    @Column(name = "disable_out_of_stock")
    private boolean disableOutOfStock;

    @Column
    private String note;

    @Column(nullable = false)
    private Date createdAt = new Date();

    @Column(nullable = false)
    private Date updatedAt = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", insertable = false, updatable = false)
    private StaffAccount createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", insertable = false, updatable = false)
    private StaffAccount updatedBy;

    // Relationship to product attributes
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttribute> productAttributes = new ArrayList<>();

    // Relationship to product gallery images
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductGallery> galleries = new ArrayList<>();
}
