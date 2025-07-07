package com.data.controller;

import com.data.entity.Product;
import com.data.repository.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepositoryImpl productRepository;

    @GetMapping
    public String listProducts(Model model) {

        model.addAttribute("products", productRepository.findAll());
        return "product-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("formAction", "/products/add");
        return "product-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Product product = productRepository.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("formAction", "/products/edit");
            return "product-form";
        }
        return "redirect:/products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product) {
        productRepository.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}