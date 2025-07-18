package com.data.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.data.modal.dto.DishDTO;
import com.data.modal.entity.Dish;
import com.data.response.ApiResponse;
import com.data.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    @PostMapping
    public ResponseEntity<ApiResponse<Dish>> createDish(@ModelAttribute DishDTO dishDTO) {
        String imageUrl = uploadImageToCloudinary(dishDTO.getImage());

        Dish dish = new Dish();
        dish.setName(dishDTO.getName());
        dish.setDescription(dishDTO.getDescription());
        dish.setPrice(dishDTO.getPrice());
        dish.setStatus(dishDTO.getStatus());
        dish.setImage(imageUrl);

        Dish savedDish = dishService.saveDish(dish);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish created successfully", savedDish));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Dish>> updateDish(@PathVariable Long id, @ModelAttribute DishDTO dishDTO) {
        String imageUrl = dishDTO.getImage() != null ? uploadImageToCloudinary(dishDTO.getImage()) : null;

        Dish updatedDish = new Dish();
        updatedDish.setName(dishDTO.getName());
        updatedDish.setDescription(dishDTO.getDescription());
        updatedDish.setPrice(dishDTO.getPrice());
        updatedDish.setStatus(dishDTO.getStatus());
        if (imageUrl != null) {
            updatedDish.setImage(imageUrl);
        }

        Dish savedDish = dishService.updateDish(id, updatedDish);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish updated successfully", savedDish));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dish deleted successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Dish>>> getAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Dishes retrieved successfully", dishes));
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
