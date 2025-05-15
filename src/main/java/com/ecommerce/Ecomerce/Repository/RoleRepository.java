package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}