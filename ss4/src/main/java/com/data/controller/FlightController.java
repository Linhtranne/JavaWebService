package com.data.controller;

import com.data.entity.Flight;
import com.data.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public String getFlights(@RequestParam(required = false) String departure,
                             @RequestParam(required = false) String destination,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             Model model) {
        Page<Flight> flightPage = flightService.getFlights(departure != null ? departure : "",
                destination != null ? destination : "",
                PageRequest.of(page, size));
        model.addAttribute("flights", flightPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", flightPage.getTotalPages());
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        return "flights";
    }
}