package com.data.service;

import com.data.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking saveBooking(Booking booking);

    List<Booking> getBookingsByCustomer(String customerName, String customerPhone);

    void cancelBooking(Long id);
}
