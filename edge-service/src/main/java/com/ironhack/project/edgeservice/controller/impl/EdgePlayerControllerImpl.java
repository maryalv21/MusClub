package com.ironhack.project.edgeservice.controller.impl;

import com.ironhack.project.edgeservice.controller.interfaces.EdgePlayerController;
import com.ironhack.project.edgeservice.models.Player;
import com.ironhack.project.edgeservice.service.interfaces.EdgePlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins="http://localhost:4200")
public class EdgePlayerControllerImpl implements EdgePlayerController {
    @Autowired
    private EdgePlayerService playerService;

    @PostMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

}
