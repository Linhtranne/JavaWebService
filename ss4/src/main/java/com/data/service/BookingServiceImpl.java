package com.data.service;

import com.data.entity.Booking;
import com.data.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByCustomer(String customerName, String customerPhone) {
        return bookingRepository.findByCustomerNameAndCustomerPhone(customerName, customerPhone);
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus("CANCELLED");
            bookingRepository.save(booking);
        }
    }
}