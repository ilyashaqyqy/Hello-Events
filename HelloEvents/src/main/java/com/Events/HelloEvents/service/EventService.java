package com.Events.HelloEvents.service;

import com.Events.HelloEvents.dto.EventDTO;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    List<EventDTO> getAllEvents();
    EventDTO getEventById(Long id);
    EventDTO createEvent(EventDTO eventDTO);
    EventDTO updateEvent(Long id, EventDTO eventDTO);
    void deleteEvent(Long id);
    List<EventDTO> searchEvents(String category, String location, LocalDate date);
}
