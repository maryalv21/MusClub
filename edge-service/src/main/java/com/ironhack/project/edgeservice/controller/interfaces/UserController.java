package com.ironhack.project.edgeservice.controller.interfaces;

import com.ironhack.project.edgeservice.controller.dto.UserDTO;
import com.ironhack.project.edgeservice.models.User;

import java.util.List;

public interface UserController {
    UserDTO register(UserDTO userDTO);
    List<UserDTO> findAll();
    UserDTO login(User user);
}
