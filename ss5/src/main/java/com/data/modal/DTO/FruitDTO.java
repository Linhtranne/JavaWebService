package com.data.modal.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FruitDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
}