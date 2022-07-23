package com.ironhack.project.edgeservice.service.interfaces;

import com.ironhack.project.edgeservice.controller.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO store(UserDTO user);
    List<UserDTO> findAll();
}
