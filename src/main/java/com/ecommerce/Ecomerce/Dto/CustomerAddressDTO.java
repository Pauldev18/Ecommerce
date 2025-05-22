package com.ecommerce.Ecomerce.Dto;

import lombok.Data;

import java.util.UUID;
@Data
public class CustomerAddressDTO {
    private UUID id;
    private UUID customerId;
    private String city;
    private String country;
    private String dialCode;
    private String addressLine1;
    private String line2;
    private String phoneNumber;
    private String postalCode;

    public CustomerAddressDTO(UUID id, UUID customerId, String city, String country, String dialCode,
                              String addressLine1, String line2, String phoneNumber, String postalCode) {
        this.id = id;
        this.customerId = customerId;
        this.city = city;
        this.country = country;
        this.dialCode = dialCode;
        this.addressLine1 = addressLine1;
        this.line2 = line2;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
    }


}