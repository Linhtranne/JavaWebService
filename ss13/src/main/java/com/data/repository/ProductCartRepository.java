package com.data.repository;

import com.data.model.entity.Product;
import com.data.model.entity.ProductCart;
import com.data.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long>{
    List<ProductCart> findAllByUserId(UUID userId);
    Optional<ProductCart> findByUserAndProduct(User user, Product product);
    Optional<ProductCart> findByUserIdAndProductId(UUID userId, Long productId);
    void deleteByUserIdAndProductId(UUID userId, Long productId);
}