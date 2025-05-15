package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}