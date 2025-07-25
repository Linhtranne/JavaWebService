package com.data.service;

import com.data.model.entity.ProductCart;
import com.data.model.dto.request.CartRequest;

import java.util.List;

public interface ProductCartService {
    void addToCart(CartRequest request);
    void removeFromCart(Long productId);
    void updateQuantity(Long productId, Integer quantity);
    List<ProductCart> getUserCart();
}