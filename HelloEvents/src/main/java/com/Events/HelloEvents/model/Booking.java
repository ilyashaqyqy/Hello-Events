package com.Events.HelloEvents.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBooking;
    private LocalDate bookingDate;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Event event;


}

