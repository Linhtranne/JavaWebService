package com.data.service;

import com.data.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightService {
    Page<Flight> getFlights(String departure, String destination, Pageable pageable);

    Flight getFlightById(Long id);
}
