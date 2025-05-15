package com.ecommerce.Ecomerce.controller;

import com.ecommerce.Ecomerce.dto.AttributeDto;

import java.util.Collections;
import java.util.List;

public class AttributeBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static AttributeDto getDto() {
        AttributeDto dto = new AttributeDto();
        dto.setId("1");
        return dto;
    }
}
