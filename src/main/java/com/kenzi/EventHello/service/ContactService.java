package com.kenzi.EventHello.service;



import com.kenzi.EventHello.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<Contact> getAllContacts();
    Optional<Contact> getContactById(Long id);
    void deleteContact(Long id);
    Long countCantacts();
}
