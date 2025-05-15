package com.ecommerce.Ecomerce.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "slideshows")
public class Slideshow {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String destinationUrl;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String image;
    private String placeholder;
    private String description;
    private String btnLabel;
    private Integer displayOrder;
    private boolean published = false;
    private Integer clicks = 0;
    private String styles;

    @Column(nullable = false)
    private Date createdAt = new Date();
    @Column(nullable = false)
    private Date updatedAt = new Date();
}
