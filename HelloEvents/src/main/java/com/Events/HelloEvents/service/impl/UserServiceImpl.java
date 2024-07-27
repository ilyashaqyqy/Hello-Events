package com.Events.HelloEvents.service.impl;

import com.Events.HelloEvents.dto.UserDTO;
import com.Events.HelloEvents.model.Users;
import com.Events.HelloEvents.repositories.UserRepository;
import com.Events.HelloEvents.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //////////                               ///////////
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        Users user = modelMapper.map(userDTO, Users.class);
        Users savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<Users> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDTO.class);
        } else {
            // Handle the case where the user is not found
            return null; // Or throw an exception
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<Users> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setRole(userDTO.getRole());
            Users updatedUser = userRepository.save(user);
            return modelMapper.map(updatedUser, UserDTO.class);
        } else {

            // case where the user is not found
            return null; // Or throw an exception
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
