package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}