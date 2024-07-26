//package com.Events.HelloEvents.service.impl;
//
//import com.Events.HelloEvents.dto.ContactDTO;
//import com.Events.HelloEvents.model.Contact;
//import com.Events.HelloEvents.model.Users;
//import com.Events.HelloEvents.repositories.ContactRepository;
//import com.Events.HelloEvents.repositories.UserRepository;
//import com.Events.HelloEvents.service.ContactService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ContactServiceImpl implements ContactService {
//
//    @Autowired
//    private ContactRepository contactRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public ContactDTO createContact(ContactDTO contactDTO) {
//        Users user = userRepository.findById(contactDTO.getUser().getIdUser())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Contact contact = new Contact();
//        contact.setName(contactDTO.getName());
//        contact.setEmail(contactDTO.getEmail());
//        contact.setSubject(contactDTO.getSubject());
//        contact.setMessage(contactDTO.getMessage());
//        contact.setSentAt(contactDTO.getSentAt());
//        contact.setUser(user);
//
//        contact = contactRepository.save(contact);
//
//        contactDTO.setIdContact(contact.getIdContact());
//        return contactDTO;
//    }
//
//    @Override
//    public List<ContactDTO> getAllContacts() {
//        return List.of();
//    }
//
//    @Override
//    public ContactDTO getContactById(Long id) {
//        return null;
//    }
//
//    @Override
//    public void deleteContact(Long id) {
//
//    }
//}
