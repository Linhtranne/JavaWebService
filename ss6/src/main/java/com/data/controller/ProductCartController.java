package com.data.controller;

import com.data.entity.ProductCart;
import com.data.entity.User;
import com.data.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ProductCartController {

    private final ProductCartService productCartService;

    @Autowired
    public ProductCartController(ProductCartService productCartService) {
        this.productCartService = productCartService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCart>> getCartItemsByUser(@RequestParam User user) {
        List<ProductCart> cartItems = productCartService.getCartItemsByUser(user);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductCart> addToCart(@RequestBody ProductCart productCart) {
        ProductCart addedCartItem = productCartService.addToCart(productCart);
        return new ResponseEntity<>(addedCartItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCart> updateQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        ProductCart updatedCartItem = productCartService.updateQuantity(id, quantity);
        return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        productCartService.removeFromCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}