package com.Events.HelloEvents.service;

import com.Events.HelloEvents.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    List<BookingDTO> getBookingsByUserId(Long userId);
}

