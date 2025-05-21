package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Dto.CartItemDTO;
import com.ecommerce.Ecomerce.Dto.CartItemRequestDTO;
import com.ecommerce.Ecomerce.Dto.CartResponseDTO;
import com.ecommerce.Ecomerce.Entity.Card;
import com.ecommerce.Ecomerce.Entity.CardItem;
import com.ecommerce.Ecomerce.Entity.Customer;
import com.ecommerce.Ecomerce.Entity.Product;
import com.ecommerce.Ecomerce.Repository.CardItemRepository;
import com.ecommerce.Ecomerce.Repository.CardRepository;
import com.ecommerce.Ecomerce.Repository.CustomerRepository;
import com.ecommerce.Ecomerce.Repository.ProductRepository;
import com.ecommerce.Ecomerce.Service.CardService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cartRepository;
    @Autowired
    private  CardItemRepository itemRepository;
    @Autowired
    private  CustomerRepository customerRepository;
    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    private CartResponseDTO mapToDTO(Card cart) {
        List<CartItemDTO> items = cart.getItems().stream()
                .map(i -> {
                    CartItemDTO dto = new CartItemDTO();
                    dto.setItemId(i.getId());
                    dto.setProductId(i.getProduct().getId());
                    dto.setQuantity(i.getQuantity());
                    return dto;
                })
                .collect(Collectors.toList());
        return new CartResponseDTO(cart.getId(), cart.getCustomer().getId(), items);
    }

    private Card getOrCreateCart(UUID customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
        return cartRepository.findByCustomerId(customerId)
                .orElseGet(() -> cartRepository.save(new Card(UUID.randomUUID(), customer, new ArrayList<>())));
    }

    @Override
    public CartResponseDTO getCartByCustomer(UUID customerId) {
        Card cart = getOrCreateCart(customerId);
        return mapToDTO(cart);
    }


    @Override
    @Transactional
    public CartResponseDTO addItem(UUID customerId, CartItemRequestDTO request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));

        TypedQuery<Card> q = entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.customer.id = :cid", Card.class);
        q.setParameter("cid", customerId);
        q.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        List<Card> list = q.getResultList();

        Card cart;
        if (list.isEmpty()) {
            cart = new Card();
            cart.setCustomer(customer);
            cart.setItems(new ArrayList<>());
            cart = entityManager.merge(cart);
            entityManager.flush();
        } else {
            cart = list.get(0);
            entityManager.refresh(cart);
        }


        UUID prodId = UUID.fromString(request.getProductId());
        Product product = productRepository.findById(prodId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        Optional<CardItem> existing = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(prodId))
                .findFirst();

        if (existing.isPresent()) {
             CardItem item = existing.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            entityManager.merge(item);
        } else {

            CardItem item = new CardItem();
            item.setCard(cart);
            item.setProduct(product);
            item.setQuantity(request.getQuantity());
            entityManager.merge(item);
            cart.getItems().add(item);
        }


        entityManager.flush();
        return mapToDTO(cart);
    }




    @Override
    @Transactional
    public CartResponseDTO updateItem(UUID customerId, CartItemRequestDTO request) {
        Card cart = getOrCreateCart(customerId);
        UUID prodId = UUID.fromString(request.getProductId());
        CardItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(prodId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Item not found in cart"));
        item.setQuantity(request.getQuantity());
        itemRepository.save(item);
        return mapToDTO(cart);
    }

    @Override
    @Transactional
    public CartResponseDTO removeItem(UUID customerId, UUID productId) {
        Card cart = getOrCreateCart(customerId);
        itemRepository.deleteByCardIdAndProductId(UUID.fromString(cart.getId().toString()), productId);
        cart.getItems().removeIf(i -> i.getProduct().getId().equals(productId));
        return mapToDTO(cart);
    }
}
