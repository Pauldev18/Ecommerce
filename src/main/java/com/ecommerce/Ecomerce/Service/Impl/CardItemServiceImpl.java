package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Entity.CardItem;
import com.ecommerce.Ecomerce.Repository.CardItemRepository;
import com.ecommerce.Ecomerce.Service.CardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardItemServiceImpl implements CardItemService {

    @Autowired
    private CardItemRepository cardItemRepository;

    @Override
    public List<CardItem> getAll() {
        return cardItemRepository.findAll();
    }

    @Override
    public CardItem getById(UUID id) {
        return cardItemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy CardItem với ID: " + id));
    }

    @Override
    public CardItem create(CardItem cardItem) {
        return cardItemRepository.save(cardItem);
    }

    @Override
    public CardItem update(UUID id, CardItem newItem) {
        CardItem existing = getById(id);
        existing.setCard(newItem.getCard());
        existing.setProduct(newItem.getProduct());
        existing.setQuantity(newItem.getQuantity());
        return cardItemRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        cardItemRepository.deleteById(id);
    }
}
