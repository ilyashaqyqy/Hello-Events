package com.Events.HelloEvents.auth;

import com.Events.HelloEvents.config.JwtService;
import com.Events.HelloEvents.model.Role;
import com.Events.HelloEvents.model.Users;
import com.Events.HelloEvents.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Users newUser = new Users();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
//        newUser.setRole(Role.USER);
        newUser.setRole(Role.ROLE_USER);
        newUser.setEmail(request.getEmail());

        Users savedUser = repository.save(newUser);

        String jwtToken = jwtService.generateToken((UserDetails) savedUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Users user = (Users) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken((UserDetails) user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
