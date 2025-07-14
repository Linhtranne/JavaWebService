package com.data.controller;

import com.data.entity.Booking;
import com.data.entity.Flight;
import com.data.service.BookingService;
import com.data.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private FlightService flightService;

    @GetMapping("/new")
    public String newBooking(@RequestParam Long flightId, Model model) {
        Flight flight = flightService.getFlightById(flightId);
        Booking booking = new Booking();
        booking.setFlight(flight);
        model.addAttribute("booking", booking);
        return "booking-form";
    }

    @PostMapping
    public String saveBooking(@ModelAttribute Booking booking) {
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("BOOKED");
        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping
    public String getBookings(@RequestParam String customerName,
                              @RequestParam String customerPhone,
                              Model model) {
        List<Booking> bookings = bookingService.getBookingsByCustomer(customerName, customerPhone);
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/{id}/cancel")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/bookings";
    }
}