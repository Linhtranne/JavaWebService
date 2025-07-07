package com.data.repository;

import com.data.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public Product findById(long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        entityManager.persist(product);
        return product;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Product product = findById(id);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    @Override
    @Transactional
    public Product update(Product product) {
        return entityManager.merge(product);
    }
}