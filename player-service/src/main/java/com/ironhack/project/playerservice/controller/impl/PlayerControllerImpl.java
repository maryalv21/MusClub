package com.ironhack.project.playerservice.controller.impl;

import com.ironhack.project.playerservice.controller.interfaces.PlayerController;
import com.ironhack.project.playerservice.models.Player;
import com.ironhack.project.playerservice.repository.PlayerRepository;
import com.ironhack.project.playerservice.service.interfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlayerControllerImpl implements PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    @PostMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    public Player createPlayer(@RequestBody @Validated Player player) {

        return playerRepository.save(player);
    }
}
