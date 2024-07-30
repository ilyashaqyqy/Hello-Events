package com.Events.HelloEvents.controller;

import com.Events.HelloEvents.dto.ContactDTO;
import com.Events.HelloEvents.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@RequestBody ContactDTO contactDTO) {
        if (contactDTO == null || contactDTO.getUser() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        ContactDTO createdContactDTO = contactService.createContact(contactDTO);
        return ResponseEntity.ok(createdContactDTO);
    }
}
