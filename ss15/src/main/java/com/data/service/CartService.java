package com.data.service;

import com.data.model.entity.Cart;
import com.data.model.entity.CartItem;
import com.data.model.entity.Product;
import com.data.model.entity.User;
import com.data.repository.CartItemRepository;
import com.data.repository.CartRepository;
import com.data.repository.ProductRepository;
import com.data.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
                       ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart getCartByUser(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUserUserId(user.getUserId())
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    @Transactional
    public void addToCart(String username, UUID productId, int quantity) {
        Cart cart = getCartByUser(username);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = cartItemRepository.findByCartCartIdAndProductProductId(cart.getCartId(), productId)
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    return newItem;
                });

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemRepository.save(cartItem);
    }

    @Transactional
    public void updateCartItemQuantity(String username, UUID productId, int quantity) {
        Cart cart = getCartByUser(username);
        CartItem cartItem = cartItemRepository.findByCartCartIdAndProductProductId(cart.getCartId(), productId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        if (quantity <= 0) {
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }

    @Transactional
    public void removeFromCart(String username, UUID productId) {
        Cart cart = getCartByUser(username);
        CartItem cartItem = cartItemRepository.findByCartCartIdAndProductProductId(cart.getCartId(), productId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItemRepository.delete(cartItem);
    }

    @Transactional
    public void clearCart(String username) {
        Cart cart = getCartByUser(username);
        cartItemRepository.deleteAll(cart.getCartItems());
    }
}