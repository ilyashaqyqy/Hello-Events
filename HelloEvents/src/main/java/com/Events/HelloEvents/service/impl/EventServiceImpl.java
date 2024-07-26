package com.Events.HelloEvents.service.impl;

import com.Events.HelloEvents.dto.EventDTO;
import com.Events.HelloEvents.exception.ResourceNotFoundException;
import com.Events.HelloEvents.model.Event;
import com.Events.HelloEvents.repositories.EventRepository;
import com.Events.HelloEvents.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        return convertToDTO(event);
    }

    @Override
    @Transactional
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = convertToEntity(eventDTO);
        event = eventRepository.save(event);
        return convertToDTO(event);
    }

    @Override
    @Transactional
    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setLocation(eventDTO.getLocation());
        event.setCategory(eventDTO.getCategory());
        event.setAvailableTickets(eventDTO.getAvailableTickets());
        event = eventRepository.save(event);
        return convertToDTO(event);
    }

    @Override
    @Transactional
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found");
        }
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventDTO> searchEvents(String category, String location, LocalDate date) {
        List<Event> events;
        if (category != null && location != null && date != null) {
            events = eventRepository.findByCategoryAndLocationAndDate(category, location, date);
        } else if (category != null && location != null) {
            events = eventRepository.findByCategoryAndLocation(category, location);
        } else if (category != null && date != null) {
            events = eventRepository.findByCategoryAndDate(category, date);
        } else if (location != null && date != null) {
            events = eventRepository.findByLocationAndDate(location, date);
        } else if (category != null) {
            events = eventRepository.findByCategory(category);
        } else if (location != null) {
            events = eventRepository.findByLocation(location);
        } else if (date != null) {
            events = eventRepository.findByDate(date);
        } else {
            events = eventRepository.findAll();
        }
        return events.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    private EventDTO convertToDTO(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }

    private Event convertToEntity(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, Event.class);
    }
}
