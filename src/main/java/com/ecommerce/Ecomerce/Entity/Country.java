package com.ecommerce.Ecomerce.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 2, nullable = false)
    private String iso;
    @Column(nullable = false)
    private String name;
    private String upperName;
    @Column(length = 3)
    private String iso3;
    private Integer numCode;
    private Integer phoneCode;
}
