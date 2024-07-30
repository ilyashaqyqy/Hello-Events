package com.kenzi.EventHello.service;



import com.kenzi.EventHello.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    List<BookingDTO> getBookingsByUserId(Long userId);
}

