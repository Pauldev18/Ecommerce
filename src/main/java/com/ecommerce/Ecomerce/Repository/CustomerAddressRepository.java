package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, UUID> {
}