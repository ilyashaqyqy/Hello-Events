package com.Events.HelloEvents.service.impl;

import com.Events.HelloEvents.dto.UserDTO;
import com.Events.HelloEvents.model.Users;
import com.Events.HelloEvents.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private Users user;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new Users();
        user.setIdUser(1L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        user.setRole("USER");

        userDTO = new UserDTO();
        userDTO.setIdUser(1L);
        userDTO.setUsername("testuser");
        userDTO.setEmail("testuser@example.com");
        userDTO.setPassword("password123");
        userDTO.setRole("USER");
    }

    @Test
    public void testRegisterUser() {
        when(userRepository.save(any(Users.class))).thenReturn(user);
        when(modelMapper.map(any(UserDTO.class), eq(Users.class))).thenReturn(user);
        when(modelMapper.map(any(Users.class), eq(UserDTO.class))).thenReturn(userDTO);

        UserDTO registeredUser = userService.registerUser(userDTO);
        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());

        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    public void testGetUserByUsername() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(modelMapper.map(any(Users.class), eq(UserDTO.class))).thenReturn(userDTO);

        UserDTO foundUser = userService.getUserByUsername("testuser");
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());

        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(Users.class))).thenReturn(user);
        when(modelMapper.map(any(Users.class), eq(UserDTO.class))).thenReturn(userDTO);

        UserDTO updatedUser = userService.updateUser(1L, userDTO);
        assertNotNull(updatedUser);
        assertEquals("testuser", updatedUser.getUsername());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(any(Users.class), eq(UserDTO.class))).thenReturn(userDTO);

        List<UserDTO> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("testuser", users.get(0).getUsername());

        verify(userRepository, times(1)).findAll();
    }
}
