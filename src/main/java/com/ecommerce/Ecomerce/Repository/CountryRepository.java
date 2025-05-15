package com.ecommerce.Ecomerce.Repository;

import com.ecommerce.Ecomerce.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}