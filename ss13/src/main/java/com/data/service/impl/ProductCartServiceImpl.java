package com.data.service.impl;

import com.data.model.dto.request.ProductCartDTO;
import com.data.model.entity.Product;
import com.data.model.entity.ProductCart;
import com.data.model.entity.User;
import com.data.model.dto.request.CartRequest;
import com.data.repository.ProductCartRepository;
import com.data.repository.ProductRepository;
import com.data.service.ProductCartService;
import com.data.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCartServiceImpl implements ProductCartService {

    private final ProductCartRepository cartRepo;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Override
    public void addToCart(CartRequest request) {
        User user = userService.getCurrentUser();
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductCart cart = cartRepo.findByUserAndProduct(user, product).orElse(null);
        if (cart == null) {
            cart = ProductCart.builder()
                    .user(user)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
        } else {
            cart.setQuantity(cart.getQuantity() + request.getQuantity());
        }
        cartRepo.save(cart);
    }

    @Override
    public void removeFromCart(Long productId) {
        User user = userService.getCurrentUser();
        cartRepo.deleteByUserIdAndProductId(user.getId(), productId);
    }

    @Override
    public void updateQuantity(Long productId, Integer quantity) {
        User user = userService.getCurrentUser();
        ProductCart cart = cartRepo.findByUserIdAndProductId(user.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cart.setQuantity(quantity);
        cartRepo.save(cart);
    }

    @Override
    public List<ProductCart> getUserCart() {
        User user = userService.getCurrentUser();
        return new ArrayList<>(cartRepo.findAllByUserId(user.getId()));
    }
}