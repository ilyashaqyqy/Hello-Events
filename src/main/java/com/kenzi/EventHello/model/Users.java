package com.kenzi.EventHello.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String username;
    private String password;
    private String email;
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Booking> bookings = new HashSet<>();
}
