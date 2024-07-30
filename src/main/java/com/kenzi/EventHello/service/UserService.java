package com.kenzi.EventHello.service;


import com.kenzi.EventHello.dto.UserDTO;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO getUserByUsername(String username);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    Long countUsers();
}

