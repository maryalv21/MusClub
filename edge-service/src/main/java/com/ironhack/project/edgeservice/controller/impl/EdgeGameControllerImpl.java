package com.ironhack.project.edgeservice.controller.impl;

import com.ironhack.project.edgeservice.client.GameProxyClient;
import com.ironhack.project.edgeservice.controller.interfaces.EdgeGameController;
import com.ironhack.project.edgeservice.models.Game;
import com.ironhack.project.edgeservice.service.interfaces.EdgeGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins="http://localhost:4200")
public class EdgeGameControllerImpl implements EdgeGameController {

    @Autowired
    private EdgeGameService edgeGameService;

    @Autowired
    private GameProxyClient gameProxyClient;


    @GetMapping("/games")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> FindAll() {
        return edgeGameService.FindAll();
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody Game game) {
        return edgeGameService.createGame(game);
    }

    @PutMapping("/games/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Game updateGame(@PathVariable Long id,  @RequestBody Game game) {
        return edgeGameService.updateGame(id, game);
    }

    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void  deleteGame(@PathVariable Long id) {
         edgeGameService.deleteGame(id);
    }
}
