package com.data.controller;

import com.data.entity.Harvest;
import com.data.service.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/harvests")
public class HarvestController {

    @Autowired
    private HarvestService harvestService;

    @GetMapping
    public List<Harvest> getAllHarvests() {
        return harvestService.getAllHarvests();
    }

    @GetMapping("/{id}")
    public Harvest getHarvestById(@PathVariable Long id) {
        return harvestService.getHarvestById(id);
    }

    @PostMapping
    public Harvest addHarvest(@RequestBody Harvest harvest) {
        return harvestService.addHarvest(harvest);
    }
}