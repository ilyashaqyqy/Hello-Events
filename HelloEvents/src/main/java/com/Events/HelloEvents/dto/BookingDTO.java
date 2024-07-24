package com.Events.HelloEvents.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingDTO {
    private Long id;
    private LocalDate bookingDate;
    private UserDTO user;
    private EventDTO event;


}

