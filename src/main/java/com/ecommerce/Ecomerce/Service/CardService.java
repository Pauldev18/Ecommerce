package com.ecommerce.Ecomerce.Service;

import com.ecommerce.Ecomerce.Dto.CartItemRequestDTO;
import com.ecommerce.Ecomerce.Dto.CartResponseDTO;
import com.ecommerce.Ecomerce.Entity.Card;

import java.util.List;
import java.util.UUID;

public interface CardService {
    CartResponseDTO getCartByCustomer(UUID customerId);
    CartResponseDTO addItem(UUID customerId, CartItemRequestDTO request);
    CartResponseDTO updateItem(UUID customerId, CartItemRequestDTO request);
    CartResponseDTO removeItem(UUID customerId, UUID productId);
}
