package com.data.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.data.modal.entity.Ingredient;
import com.data.response.ApiResponse;
import com.data.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    @PostMapping
    public ResponseEntity<ApiResponse<Ingredient>> createIngredient(@RequestParam String name,
                                                                     @RequestParam Integer stock,
                                                                     @RequestParam String expiry,
                                                                     @RequestParam MultipartFile image) {
        String imageUrl = uploadImageToCloudinary(image);

        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setStock(stock);
        ingredient.setExpiry(LocalDate.parse(expiry));
        ingredient.setImage(imageUrl);

        Ingredient savedIngredient = ingredientService.saveIngredient(ingredient);
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingredient created successfully", savedIngredient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Ingredient>> updateIngredient(@PathVariable Long id,
                                                                     @RequestParam String name,
                                                                     @RequestParam Integer stock,
                                                                     @RequestParam String expiry,
                                                                     @RequestParam(required = false) MultipartFile image) {
        String imageUrl = image != null ? uploadImageToCloudinary(image) : null;

        Ingredient updatedIngredient = new Ingredient();
        updatedIngredient.setName(name);
        updatedIngredient.setStock(stock);
        updatedIngredient.setExpiry(LocalDate.parse(expiry));
        if (imageUrl != null) {
            updatedIngredient.setImage(imageUrl);
        }

        Ingredient savedIngredient = ingredientService.updateIngredient(id, updatedIngredient);
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingredient updated successfully", savedIngredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingredient deleted successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Ingredient>>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingredients retrieved successfully", ingredients));
    }

    private String uploadImageToCloudinary(MultipartFile file) {
        try {
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", cloudName,
                    "api_key", apiKey,
                    "api_secret", apiSecret
            ));

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }
}