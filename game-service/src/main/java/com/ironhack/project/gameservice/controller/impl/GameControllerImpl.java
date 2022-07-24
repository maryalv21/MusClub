package com.ironhack.project.gameservice.controller.impl;

import com.ironhack.project.gameservice.controller.interfaces.GameController;
import com.ironhack.project.gameservice.models.Game;
import com.ironhack.project.gameservice.repository.GameRepository;
import com.ironhack.project.gameservice.service.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class GameControllerImpl implements GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepository;

    //Method to Find All Games for player and Member
    @GetMapping("/games")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> FindAll() {
        List<Game> gameList = gameService.FindAll();
        return gameList;
    }

    //Method to create Game for Member
    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    //Method to update Game for Member
    @PutMapping("/games/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Game updateGame(@PathVariable Long id, @RequestBody Game game) {
        gameService.updateGame(id, game);
        return game;
    }

    //Method to delete Game for Member
    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
    }

    
}
