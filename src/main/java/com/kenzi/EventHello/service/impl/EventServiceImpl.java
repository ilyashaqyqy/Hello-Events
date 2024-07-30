package com.kenzi.EventHello.service.impl;

import com.kenzi.EventHello.dto.EventDTO;
import com.kenzi.EventHello.exeption.ResourceNotFoundException;
import com.kenzi.EventHello.model.Event;
import com.kenzi.EventHello.repositories.EventRepository;
import com.kenzi.EventHello.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Long countEvents() {
        return eventRepository.countEvents();
    }

    private EventDTO convertToDTO(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }

    private Event convertToEntity(EventDTO eventDTO) {
        return modelMapper.map(eventDTO, Event.class);
    }
}
