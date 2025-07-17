package com.data.service;

import com.data.entity.Seed;
import com.data.repository.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedService {

    @Autowired
    private SeedRepository seedRepository;

    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }

    public Seed getSeedById(Long id) {
        return seedRepository.findById(id).orElseThrow(() -> new RuntimeException("Seed not found"));
    }

    public Seed addSeed(Seed seed) {
        return seedRepository.save(seed);
    }

    public Seed updateSeed(Long id, Seed seed) {
        Seed existingSeed = seedRepository.findById(id).orElseThrow(() -> new RuntimeException("Seed not found"));
        existingSeed.setSeedName(seed.getSeedName());
        existingSeed.setQuantity(seed.getQuantity());
        return seedRepository.save(existingSeed);
    }

    public void deleteSeed(Long id) {
        seedRepository.deleteById(id);
    }
}