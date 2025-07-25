package com.data.controller;

import com.data.model.dto.request.CartRequest;
import com.data.model.dto.request.ProductCartDTO;
import com.data.model.entity.ProductCart;
import com.data.service.ProductCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ProductCartController {
    private final ProductCartService productCartService;

    @PostMapping
    public ResponseEntity<Void> addToCart(@RequestBody CartRequest cartRequest) {
        productCartService.addToCart(cartRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateQuantity(@PathVariable Long productId, @RequestParam Integer quantity) {
        productCartService.updateQuantity(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long productId) {
        productCartService.removeFromCart(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductCartController>> getUserCart() {
        List<ProductCartController> cartItems = productCartService.getUserCart();
        return ResponseEntity.ok(cartItems);
    }
}