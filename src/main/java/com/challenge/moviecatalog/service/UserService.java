package com.challenge.moviecatalog.service;

import com.challenge.moviecatalog.model.domain.Role;
import com.challenge.moviecatalog.model.domain.User;
import com.challenge.moviecatalog.model.repository.RoleRepository;
import com.challenge.moviecatalog.model.repository.UserRepository;
import com.challenge.moviecatalog.web.controller.exceptions.ResourceNotFoundException;
import com.challenge.moviecatalog.web.requests.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public User register(UserRequest userRequest) {
        Role role = roleRepository.findByName(userRequest.getRole().getName())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found. User admin or user"));
        User user = new User();
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(role);

        return userRepository.save(user);
    }

}
