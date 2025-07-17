package com.data.controller;

import com.data.entity.Seed;
import com.data.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seeds")
public class SeedController {

    @Autowired
    private SeedService seedService;

    @GetMapping
    public List<Seed> getAllSeeds() {
        return seedService.getAllSeeds();
    }

    @GetMapping("/{id}")
    public Seed getSeedById(@PathVariable Long id) {
        return seedService.getSeedById(id);
    }

    @PostMapping
    public Seed addSeed(@RequestBody Seed seed) {
        return seedService.addSeed(seed);
    }

    @PutMapping("/{id}")
    public Seed updateSeed(@PathVariable Long id, @RequestBody Seed seed) {
        return seedService.updateSeed(id, seed);
    }

    @DeleteMapping("/{id}")
    public void deleteSeed(@PathVariable Long id) {
        seedService.deleteSeed(id);
    }
}