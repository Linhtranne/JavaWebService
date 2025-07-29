package com.data.controller;

import com.data.model.entity.*;
import com.data.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    public AdminController(UserService userService, RoleService roleService, ProductService productService,
                           CategoryService categoryService, OrderService orderService) {
        this.userService = userService;
        this.roleService = roleService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/users/{userId}/toggle-enabled")
    public ResponseEntity<?> toggleUserEnabled(@PathVariable UUID userId) {
        userService.toggleUserEnabled(userId);
        return ResponseEntity.ok("Cập nhật trạng thái người dùng thành công");
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String keyword) {
        return ResponseEntity.ok(userService.searchUsers(keyword));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID productId, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(productId, product));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Xóa sản phẩm thành công");
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/categories/{cateId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID cateId) {
        return ResponseEntity.ok(categoryService.getCategoryById(cateId));
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @PutMapping("/categories/{cateId}")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID cateId, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.updateCategory(cateId, category));
    }

    @DeleteMapping("/categories/{cateId}")
    public ResponseEntity<?> deleteCategory(@PathVariable UUID cateId) {
        categoryService.deleteCategory(cateId);
        return ResponseEntity.ok("Xóa danh mục thành công");
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/orders/status")
    public ResponseEntity<List<Order>> getOrdersByStatus(@RequestParam Order.OrderStatus status) {
        return ResponseEntity.ok(orderService.getOrdersByStatus(status));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PutMapping("/orders/{orderId}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable UUID orderId, @RequestBody Order.OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("Cập nhật trạng thái đơn hàng thành công");
    }
}
