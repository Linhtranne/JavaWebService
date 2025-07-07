package com.data.repository;

    import com.data.entity.Product;
    import java.util.List;

    public interface ProductRepository {
        List<Product> findAll();
        Product findById(long id);
        Product save(Product product);
        void deleteById(long id);
        Product update(Product product);
    }