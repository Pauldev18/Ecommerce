package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.Country;
import com.ecommerce.Ecomerce.Repository.CountryRepository;
import com.ecommerce.Ecomerce.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country getById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy Country với ID: " + id));
    }

    @Override
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country update(Integer id, Country newCountry) {
        Country existing = getById(id);
        existing.setIso(newCountry.getIso());
        existing.setName(newCountry.getName());
        existing.setUpperName(newCountry.getUpperName());
        existing.setIso3(newCountry.getIso3());
        existing.setNumCode(newCountry.getNumCode());
        existing.setPhoneCode(newCountry.getPhoneCode());
        return countryRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        countryRepository.deleteById(id);
    }
}
