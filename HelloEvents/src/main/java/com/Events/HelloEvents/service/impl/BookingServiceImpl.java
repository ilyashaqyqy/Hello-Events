package com.Events.HelloEvents.service.impl;

import com.Events.HelloEvents.dto.BookingDTO;
import com.Events.HelloEvents.dto.UserDTO;
import com.Events.HelloEvents.dto.EventDTO;
import com.Events.HelloEvents.model.Booking;
import com.Events.HelloEvents.model.Users;
import com.Events.HelloEvents.model.Event;
import com.Events.HelloEvents.repositories.BookingRepository;
import com.Events.HelloEvents.repositories.UserRepository;
import com.Events.HelloEvents.repositories.EventRepository;
import com.Events.HelloEvents.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        if (bookingDTO == null) {
            throw new IllegalArgumentException("BookingDTO cannot be null");
        }

        Long userId = bookingDTO.getUser() != null ? bookingDTO.getUser().getIdUser() : null;
        Long eventId = bookingDTO.getEvent() != null ? bookingDTO.getEvent().getIdEvent() : null;

        if (userId == null || eventId == null) {
            throw new IllegalArgumentException("User ID and Event ID cannot be null");
        }

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Booking booking = new Booking();
        booking.setBookingDate(bookingDTO.getBookingDate() != null ? bookingDTO.getBookingDate() : LocalDate.now());
        booking.setUser(user);
        booking.setEvent(event);

        booking = bookingRepository.save(booking);

        BookingDTO savedBookingDTO = new BookingDTO();
        savedBookingDTO.setIdBooking(booking.getIdBooking());
        savedBookingDTO.setBookingDate(booking.getBookingDate());
        savedBookingDTO.setUser(convertToDTO(user));
        savedBookingDTO.setEvent(convertToDTO(event));

        return savedBookingDTO;
    }


    @Override
    public List<BookingDTO> getBookingsByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserIdUser(userId);
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setIdBooking(booking.getIdBooking());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setUser(convertToDTO(booking.getUser()));
        bookingDTO.setEvent(convertToDTO(booking.getEvent()));
        return bookingDTO;
    }

    private Users convertToEntity(UserDTO userDTO) {
        Users user = new Users();
        user.setIdUser(userDTO.getIdUser());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole()); // Ensure UserDTO has role if needed
        return user;
    }

    private UserDTO convertToDTO(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(user.getIdUser());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    private Event convertToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setIdEvent(eventDTO.getIdEvent());
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setLocation(eventDTO.getLocation());
        event.setCategory(eventDTO.getCategory());
        event.setAvailableTickets(eventDTO.getAvailableTickets());
        return event;
    }

    private EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setIdEvent(event.getIdEvent());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setDate(event.getDate());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setCategory(event.getCategory());
        eventDTO.setAvailableTickets(event.getAvailableTickets());
        return eventDTO;
    }
}
