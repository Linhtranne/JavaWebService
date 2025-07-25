package com.data.model.dto.request;

import com.data.model.entity.Product;
import com.data.model.entity.User;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCartDTO {

    private UUID id;

    private User user;

    private Product product;

    private Integer quantity;
}