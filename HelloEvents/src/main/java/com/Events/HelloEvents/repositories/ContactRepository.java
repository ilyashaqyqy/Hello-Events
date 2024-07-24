package com.Events.HelloEvents.repositories;

import com.Events.HelloEvents.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}

