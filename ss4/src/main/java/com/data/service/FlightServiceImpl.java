package com.data.service;

import com.data.entity.Flight;
import com.data.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Page<Flight> getFlights(String departure, String destination, Pageable pageable) {
        return flightRepository.findByDepartureContainingAndDestinationContaining(departure, destination, pageable);
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }
}