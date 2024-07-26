package com.Events.HelloEvents.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ContactDTO {
    private Long idContact;
    private String name;
    private String email;
    private String subject;
    private String message;
    private LocalDateTime sentAt;
    private UserDTO user;
}

