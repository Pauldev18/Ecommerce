package com.ecommerce.Ecomerce.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private StaffAccount account;

    private String title;
    private String content;
    private boolean seen = false;
    @Column(nullable = false)
    private Date createdAt = new Date();
    private Date receiveTime;
    private Date notificationExpiryDate;
}