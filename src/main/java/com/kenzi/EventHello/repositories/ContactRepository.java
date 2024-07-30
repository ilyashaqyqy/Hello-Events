package com.kenzi.EventHello.repositories;
import com.kenzi.EventHello.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("select count (*) from Contact")
   Long countCantacts();
}

