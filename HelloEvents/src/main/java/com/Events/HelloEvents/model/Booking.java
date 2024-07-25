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
    @JoinColumn(name = "user_id", referencedColumnName = "idUser", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "idEvent", nullable = false)
    private Event event;
}
