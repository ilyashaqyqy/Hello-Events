package com.kenzi.EventHello.controller;


import com.kenzi.EventHello.service.ContactService;
import com.kenzi.EventHello.service.EventService;

import com.kenzi.EventHello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class DashboardController {

    @Autowired
    private EventService eventService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;


    @GetMapping("/contacts")
    public Long countClients (){
        return contactService.countCantacts();
    }
     @GetMapping("/users")
   public Long countUsers(){
        return userService.countUsers();
     }


    @GetMapping("/events")
    public Long countEvents() {
        return eventService.countEvents();
    }
}
