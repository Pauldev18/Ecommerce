package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.Card;
import com.ecommerce.Ecomerce.Repository.CardRepository;
import com.ecommerce.Ecomerce.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card getById(UUID id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy Card với ID: " + id));
    }

    @Override
    public Card create(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card update(UUID id, Card newCard) {
        Card existing = getById(id);
        existing.setCustomer(newCard.getCustomer());
        return cardRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        cardRepository.deleteById(id);
    }
}
