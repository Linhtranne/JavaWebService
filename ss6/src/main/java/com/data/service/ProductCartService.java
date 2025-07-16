package com.data.service;

import com.data.entity.ProductCart;
import com.data.entity.User;
import com.data.repository.ProductCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCartService {

    private final ProductCartRepository productCartRepository;

    @Autowired
    public ProductCartService(ProductCartRepository productCartRepository) {
        this.productCartRepository = productCartRepository;
    }

    public List<ProductCart> getCartItemsByUser(User user) {
        return productCartRepository.findByUser(user);
    }

    public ProductCart addToCart(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }

    public ProductCart updateQuantity(Long id, Integer quantity) {
        return productCartRepository.findById(id)
                .map(cartItem -> {
                    cartItem.setQuantity(quantity);
                    return productCartRepository.save(cartItem);
                })
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng với id: " + id));
    }

    public void removeFromCart(Long id) {
        productCartRepository.deleteById(id);
    }
}