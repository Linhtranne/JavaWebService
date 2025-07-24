package com.data.session12.service.product;


import com.data.session12.model.dto.reqest.ProductRequestDTO;
import com.data.session12.model.entity.Product;
import com.data.session12.model.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final com.data.session12.repo.ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(ProductRequestDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductMapper.updateEntity(product, dto);
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
