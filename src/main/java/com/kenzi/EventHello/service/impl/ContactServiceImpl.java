package com.kenzi.EventHello.service.impl;

import com.kenzi.EventHello.model.Contact;
import com.kenzi.EventHello.repositories.ContactRepository;
import com.kenzi.EventHello.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
   @Autowired
    private ContactRepository contactRepository;


    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public void deleteContact(Long id) {
       contactRepository.deleteById(id);
    }

    @Override
    public Long countCantacts() {
        return contactRepository.countCantacts();
    }
}
