package com.data.service;

import com.data.modal.DTO.FruitDTO;
import com.data.modal.entity.Fruit;
import com.data.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FruitService {

    @Autowired
    private FruitRepository fruitRepository;

    public List<FruitDTO> getAllFruits() {
        return fruitRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FruitDTO getFruitById(Long id) {
        Optional<Fruit> fruit = fruitRepository.findById(id);
        return fruit.map(this::convertToDTO).orElse(null);
    }

    public Fruit createFruit(Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    public Fruit updateFruit(Long id, Fruit fruit) {
        Optional<Fruit> existingFruit = fruitRepository.findById(id);
        if (existingFruit.isPresent()) {
            fruit.setId(id);
            return fruitRepository.save(fruit);
        } else {
            return null;
        }
    }

    public void deleteFruit(Long id) {
        fruitRepository.deleteById(id);
    }

    private FruitDTO convertToDTO(Fruit fruit) {
        return new FruitDTO(fruit.getId(), fruit.getName(), fruit.getPrice(), fruit.getStock());
    }
}