package com.data.service;

import com.data.entity.Theater;
import com.data.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService implements IService<Theater, Long> {

    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }

    @Override
    public Theater save(Theater theater) {
        return theaterRepository.save(theater);
    }

    @Override
    public Optional<Theater> findById(Long id) {
        return theaterRepository.findById(id);
    }

    @Override
    public Theater update(Theater theater) {
        if (theaterRepository.existsById(theater.getId())) {
            return theaterRepository.save(theater);
        }
        throw new IllegalArgumentException("Không tìm thấy rạp với ID: " + theater.getId());
    }

    @Override
    public void delete(Long id) {
        theaterRepository.deleteById(id);
    }
}