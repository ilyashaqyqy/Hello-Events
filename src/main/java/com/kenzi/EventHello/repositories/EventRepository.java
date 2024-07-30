package com.kenzi.EventHello.repositories;


import com.kenzi.EventHello.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCategory(String category);
    List<Event> findByLocation(String location);
    List<Event> findByDate(LocalDate date);
    List<Event> findByIdEvent(Long idEvent);
    @Query("SELECT count (*) from Event")
    Long countEvents();
}

