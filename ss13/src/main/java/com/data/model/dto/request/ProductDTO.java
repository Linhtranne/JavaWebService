package com.data.model.dto.request;
import lombok.Builder;
import lombok.Data;

@Data

@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;

    public static ProductDTO fromEntity(com.data.model.entity.Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}