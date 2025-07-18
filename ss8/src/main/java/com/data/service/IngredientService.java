package com.data.service;

import com.data.modal.entity.Ingredient;
import com.data.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient updatedIngredient) {
        Ingredient existingIngredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ingredient not found with id: " + id));
        existingIngredient.setName(updatedIngredient.getName());
        existingIngredient.setStock(updatedIngredient.getStock());
        existingIngredient.setExpiry(updatedIngredient.getExpiry());
        existingIngredient.setImage(updatedIngredient.getImage());
        return ingredientRepository.save(existingIngredient);
    }

    public void deleteIngredient(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new NoSuchElementException("Ingredient not found with id: " + id);
        }
        ingredientRepository.deleteById(id);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}