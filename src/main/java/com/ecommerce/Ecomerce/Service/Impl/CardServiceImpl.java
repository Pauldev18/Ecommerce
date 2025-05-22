package com.ecommerce.Ecomerce.Service.Impl;

import com.ecommerce.Ecomerce.Dto.*;
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
        List<CartItemDTO> itemDTOs = cart.getItems().stream().map(i -> {
            Product p = i.getProduct();

            // Map gallery
            List<ProductGalleryDTO> gallery = p.getGalleries().stream().map(g -> {
                ProductGalleryDTO gd = new ProductGalleryDTO();
                gd.setId(g.getId());
                gd.setImage(g.getImage());
                gd.setThumbnail(g.isThumbnail());
                return gd;
            }).collect(Collectors.toList());

            // Map attributes + values
            List<ProductAttributeDTO2> attrs = p.getProductAttributes().stream().map(pa -> {
                ProductAttributeDTO2 pad = new ProductAttributeDTO2();
                pad.setId(pa.getId());
                pad.setName(pa.getAttribute().getName());

                List<AttributeValueDTO> values = pa.getValues().stream().map(v -> {
                    AttributeValueDTO av = new AttributeValueDTO();
                    av.setId(v.getId());
                    av.setValue(v.getAttributeValue().getValue());
                    return av;
                }).collect(Collectors.toList());

                pad.setValues(values);
                return pad;
            }).collect(Collectors.toList());

            // Xây DTO chính
            CartItemDTO dto = new CartItemDTO();
            dto.setItemId(i.getId());
            dto.setProductId(p.getId());
            dto.setProductName(p.getName());
            dto.setUnitPrice(p.getSalePrice());
            dto.setQuantity(i.getQuantity());
            dto.setAttributes(attrs);
            dto.setGallery(gallery);

            return dto;
        }).collect(Collectors.toList());

        return new CartResponseDTO(
                cart.getId(),
                cart.getCustomer().getId(),
                itemDTOs
        );
    }


    private Card getOrCreateCart(UUID customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
        return cartRepository.findByCustomerId(customerId)
                .orElseGet(() -> cartRepository.save(new Card(UUID.randomUUID(), customer, new ArrayList<>())));
    }
    private Card getCart(UUID customerId) {
        return cartRepository.findByCustomerId(customerId).orElse(null);
    }

    @Override
    public CartResponseDTO getCartByCustomer(UUID customerId) {
        Card cart = getCart(customerId);
        if (cart == null) {
            return null;
        }
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

        // Tìm item đã có product đó (giả sử không có attributes phân biệt)
        Optional<CardItem> existing = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(prodId))
                .findFirst();

        if (existing.isPresent()) {
            // Cộng dồn số lượng
            CardItem item = existing.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            entityManager.merge(item);
        } else {
            CardItem item = new CardItem();
            item.setCard(cart);
            item.setProduct(product);
            item.setQuantity(request.getQuantity());

            if (cart.getItems() == null) {
                cart.setItems(new ArrayList<>());
            }
            cart.getItems().add(item);

            entityManager.persist(item);
            entityManager.merge(cart);
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
