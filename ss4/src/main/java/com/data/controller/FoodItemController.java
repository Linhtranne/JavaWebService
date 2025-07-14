package com.data.controller;

import com.data.entity.FoodItem;
import com.data.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/food-items")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public String getFoodItems(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Long categoryId,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               Model model) {
        Page<FoodItem> foodItemPage = foodItemService.getFoodItems(name, categoryId, PageRequest.of(page, size));
        model.addAttribute("foodItems", foodItemPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", foodItemPage.getTotalPages());
        model.addAttribute("name", name);
        model.addAttribute("categoryId", categoryId);
        return "food-items";
    }

    @GetMapping("/new")
    public String newFoodItem(Model model) {
        model.addAttribute("foodItem", new FoodItem());
        return "food-item-form";
    }

    @PostMapping
    public String saveFoodItem(@ModelAttribute FoodItem foodItem) {
        foodItemService.saveFoodItem(foodItem);
        return "redirect:/food-items";
    }

    @GetMapping("/{id}/edit")
    public String editFoodItem(@PathVariable Long id, Model model) {
        FoodItem foodItem = foodItemService.getFoodItemById(id);
        model.addAttribute("foodItem", foodItem);
        return "food-item-form";
    }

    @PostMapping("/{id}/delete")
    public String deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
        return "redirect:/food-items";
    }
}
