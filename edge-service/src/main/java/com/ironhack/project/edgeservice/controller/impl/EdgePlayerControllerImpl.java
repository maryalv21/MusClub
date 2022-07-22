package com.ironhack.project.edgeservice.controller.impl;

import com.ironhack.project.edgeservice.controller.interfaces.EdgePlayerController;
import com.ironhack.project.edgeservice.models.Player;
import com.ironhack.project.edgeservice.service.interfaces.EdgePlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EdgePlayerControllerImpl implements EdgePlayerController {

    @Autowired
    private EdgePlayerService playerService;

    @PostMapping("/players")
    @ResponseStatus(HttpStatus.CREATED)
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

}
