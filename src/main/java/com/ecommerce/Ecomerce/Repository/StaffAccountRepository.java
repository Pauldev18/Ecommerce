package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.StaffAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StaffAccountRepository extends JpaRepository<StaffAccount, UUID> {
}