package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.CardItem;

import java.util.List;
import java.util.UUID;

public interface CardItemService {
    List<CardItem> getAll();
    CardItem getById(UUID id);
    CardItem create(CardItem cardItem);
    CardItem update(UUID id, CardItem cardItem);
    void delete(UUID id);
}
