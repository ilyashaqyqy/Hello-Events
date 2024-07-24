package com.Events.HelloEvents.service;

import com.Events.HelloEvents.dto.ContactDTO;

import java.util.List;

public interface ContactService {
    ContactDTO createContact(ContactDTO contactDTO);
    List<ContactDTO> getAllContacts();
    ContactDTO getContactById(Long id);
    void deleteContact(Long id);
}
