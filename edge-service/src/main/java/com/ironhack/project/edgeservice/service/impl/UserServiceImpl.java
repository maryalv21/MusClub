package com.ironhack.project.edgeservice.service.impl;

import com.ironhack.project.edgeservice.controller.dto.RoleDTO;
import com.ironhack.project.edgeservice.controller.dto.UserDTO;
import com.ironhack.project.edgeservice.models.Role;
import com.ironhack.project.edgeservice.models.User;
import com.ironhack.project.edgeservice.repository.UserRepository;
import com.ironhack.project.edgeservice.service.interfaces.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(EdgeGameServiceImpl.class);


    @CircuitBreaker(name= "store", fallbackMethod= "storeFallback")
    public UserDTO store(UserDTO userDTO) {
        User user = toModel(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        user.setRoles(Set.of(new Role(null, "USER", user)));
        User savedUser = userRepository.save(user);
        savedUser.setPassword(null);
        return toDTO(savedUser);
    }

    public UserDTO storeFallback(Exception e){
        logger.error(e.getMessage());
        throw new RuntimeException("Sorry serve not available, try later");
    }

    @CircuitBreaker(name= "findAll", fallbackMethod= "findAllFallback")
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> response = users.stream().map(this::toDTO).toList();

        return response;
    }

    public List<UserDTO> findAllFallback(Exception e){
        logger.error(e.getMessage());
        throw new RuntimeException("Sorry serve not available, try later");
    }

    private User toModel(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    private UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles().stream().map(this::roleToDTO).collect(Collectors.toSet()));

        return userDTO;
    }

    private RoleDTO roleToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());

        return roleDTO;
    }
}
