package com.ironhack.project.gameservice.controller.impl;

import com.ironhack.project.gameservice.controller.dto.GameDTO;
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

    @GetMapping("/games/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game findById(@PathVariable Long id) {
        Game game = gameService.findById(id);
        return game ;
    }

    @GetMapping("/usergame/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameDTO> findGameByUserId(@PathVariable (name="id") Long id) {
        List<GameDTO> gameDTOList = gameService.findGameByUserId(id);
        return gameDTOList;
    }

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
    public GameDTO updateGame(@PathVariable Long id, @RequestBody GameDTO gameDTO) {
        gameService.updateGame(id, gameDTO);
        return gameDTO;
    }

    //Method to delete Game for Member
    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
    }

    
}
