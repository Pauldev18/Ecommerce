package com.ecommerce.Ecomerce.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "tag_name", nullable = false)
    private String name;
    private String icon;

    @Column(nullable = false)
    private Date createdAt = new Date();
    @Column(nullable = false)
    private Date updatedAt = new Date();
}