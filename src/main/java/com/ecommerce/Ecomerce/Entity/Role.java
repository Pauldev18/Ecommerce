package com.ecommerce.Ecomerce.Entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType. UUID)
    private UUID id;

    @Column(nullable = true)
    private String role_name;

    @Column(nullable = true)
    private String privileges;
}