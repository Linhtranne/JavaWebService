package com.data.service;

import com.data.entity.Seat;
import com.data.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService implements IService<Seat, Long> {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public Seat update(Seat seat) {
        if (seatRepository.existsById(seat.getId())) {
            return seatRepository.save(seat);
        }
        throw new IllegalArgumentException("Không tìm thấy ghế với ID: " + seat.getId());
    }

    @Override
    public void delete(Long id) {
        seatRepository.deleteById(id);
    }
}