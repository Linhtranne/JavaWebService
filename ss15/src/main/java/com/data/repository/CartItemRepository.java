package com.data.repository;

import com.data.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    Optional<CartItem> findByCartCartIdAndProductProductId(UUID cartId, UUID productId);
}