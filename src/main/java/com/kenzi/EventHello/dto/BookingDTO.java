package com.kenzi.EventHello.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingDTO {
    private Long idBooking;
    private LocalDate bookingDate;
    private UserDTO user;
    private EventDTO event;


}

