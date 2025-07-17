package com.data.controller;

import com.data.repository.*;
import com.data.entity.*;
import com.data.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public List<User> getAll() { return userRepo.findAll(); }

    @GetMapping("/{id}") public User get(@PathVariable Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepo.save(user);
    }

    @PutMapping("/{id}") public User update(@PathVariable Long id, @RequestBody User u) {
        User old = userRepo.findById(id).orElseThrow();
        old.setFullName(u.getFullName());
        old.setEmail(u.getEmail());
        old.setPhone(u.getPhone());
        old.setAddress(u.getAddress());
        return userRepo.save(old);
    }

    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) {
        userRepo.deleteById(id);
    }
}