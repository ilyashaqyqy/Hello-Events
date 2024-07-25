package com.Events.HelloEvents.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class EventDTO {
    private Long idEvent;
    private String name;
    private String description;
    private LocalDate date;
    private String location;
    private String category;
    private int availableTickets;


}
