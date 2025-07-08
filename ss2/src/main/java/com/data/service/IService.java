package com.data.service;
import java.util.List;
import java.util.Optional;

public interface IService<T, ID> {
    List<T> findAll();
    T save(T entity);
    Optional<T> findById(ID id);
    T update(T entity);
    void delete(ID id);
}