package com.data.model.mapper;

import com.data.model.dto.reqest.ProductRequestDTO;
import com.data.model.dto.response.ProductResponseDTO;
import com.data.model.entity.Product;

public class ProductMapper {
    public static ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getStock(),
                product.getDescription(),
                product.getImage()
        );
    }

    public static Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        updateEntity(product, dto);
        return product;
    }

    public static void updateEntity(Product product, ProductRequestDTO dto) {
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());
    }
}
