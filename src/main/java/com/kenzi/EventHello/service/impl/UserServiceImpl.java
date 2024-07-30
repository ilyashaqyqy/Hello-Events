package com.kenzi.EventHello.service.impl;

import com.kenzi.EventHello.dto.UserDTO;
import com.kenzi.EventHello.model.Users;
import com.kenzi.EventHello.repositories.UserRepository;
import com.kenzi.EventHello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
     private UserRepository userRepository;
    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public Long countUsers() {
        return userRepository.countUsers();
    }
}
