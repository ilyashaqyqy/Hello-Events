package com.Events.HelloEvents.controller;

import com.Events.HelloEvents.dto.BookingDTO;
import com.Events.HelloEvents.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
            return ResponseEntity.ok(createdBooking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/{userId}")
    public List<BookingDTO> getBookingsByUserId(@PathVariable Long userId) {
        return bookingService.getBookingsByUserId(userId);
    }
}
