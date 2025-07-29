package com.data.service;

import com.data.model.entity.Product;
import com.data.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCaseOrProducerContainingIgnoreCase(keyword, keyword);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getNewProducts() {
        return productRepository.findAll().stream()
                .sorted((p1, p2) -> p2.getYearMaking() - p1.getYearMaking())
                .limit(10)
                .toList();
    }

    public List<Product> getProductsByCategory(UUID cateId) {
        return productRepository.findByCategoryCateId(cateId);
    }

    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(UUID productId, Product product) {
        Product existing = getProductById(productId);
        existing.setProductName(product.getProductName());
        existing.setProducer(product.getProducer());
        existing.setYearMaking(product.getYearMaking());
        existing.setExpireDate(product.getExpireDate());
        existing.setQuantity(product.getQuantity());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());
        return productRepository.save(existing);
    }

    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }
}