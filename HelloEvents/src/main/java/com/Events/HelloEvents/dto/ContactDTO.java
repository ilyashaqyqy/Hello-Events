package com.Events.HelloEvents.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ContactDTO {
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private LocalDateTime sentAt;

}

