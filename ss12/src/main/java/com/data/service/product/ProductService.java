package com.data.session12.service.product;


import com.data.session12.model.dto.reqest.ProductRequestDTO;
import com.data.session12.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product create(ProductRequestDTO dto);
    Product update(Long id, ProductRequestDTO dto);
    void delete(Long id);
}
