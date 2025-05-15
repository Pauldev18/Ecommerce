package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Entity.Card;

import java.util.List;
import java.util.UUID;

public interface CardService {
    List<Card> getAll();
    Card getById(UUID id);
    Card create(Card card);
    Card update(UUID id, Card card);
    void delete(UUID id);
}
