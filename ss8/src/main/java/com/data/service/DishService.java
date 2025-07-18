package com.data.service;
import com.data.modal.entity.Dish;
import com.data.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, Dish updatedDish) {
        Dish existingDish = dishRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Dish not found with id: " + id));
        existingDish.setName(updatedDish.getName());
        existingDish.setDescription(updatedDish.getDescription());
        existingDish.setPrice(updatedDish.getPrice());
        existingDish.setStatus(updatedDish.getStatus());
        existingDish.setImage(updatedDish.getImage());
        return dishRepository.save(existingDish);
    }

    public void deleteDish(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new NoSuchElementException("Dish not found with id: " + id);
        }
        dishRepository.deleteById(id);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }
}