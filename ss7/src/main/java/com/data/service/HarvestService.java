package com.data.service;

import com.data.entity.Harvest;
import com.data.repository.HarvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HarvestService {

    @Autowired
    private HarvestRepository harvestRepository;

    public List<Harvest> getAllHarvests() {
        return harvestRepository.findAll();
    }

    public Harvest getHarvestById(Long id) {
        return harvestRepository.findById(id).orElseThrow(() -> new RuntimeException("Harvest not found"));
    }

    public Harvest addHarvest(Harvest harvest) {
        return harvestRepository.save(harvest);
    }
}