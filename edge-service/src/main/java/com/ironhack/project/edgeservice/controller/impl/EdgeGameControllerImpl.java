package com.ironhack.project.edgeservice.controller.impl;

import com.ironhack.project.edgeservice.client.GameProxyClient;
import com.ironhack.project.edgeservice.controller.dto.GameDTO;
import com.ironhack.project.edgeservice.controller.interfaces.EdgeGameController;
import com.ironhack.project.edgeservice.models.Game;
import com.ironhack.project.edgeservice.service.interfaces.EdgeGameService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class EdgeGameControllerImpl implements EdgeGameController {

    @Autowired
    private EdgeGameService edgeGameService;

    @Autowired
    private GameProxyClient gameProxyClient;

    private Logger logger = LoggerFactory.getLogger(EdgeGameControllerImpl.class);

    @GetMapping("/games/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game findById(@PathVariable Long id) {
        return edgeGameService.findById(id);
    }


    @GetMapping("/games")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> FindAll() {
        return edgeGameService.FindAll();
    }

    @GetMapping("/usergame/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameDTO> findGameByUserId(@PathVariable (name="id") Long id){
        return edgeGameService.findGameByUserId(id);
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody Game game) {
        return edgeGameService.createGame(game);
    }

    @PutMapping("/games/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GameDTO updateGame(@PathVariable Long id,  @RequestBody GameDTO gameDTO) {
        return edgeGameService.updateGame(id, gameDTO);
    }

    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void  deleteGame(@PathVariable Long id) {
         edgeGameService.deleteGame(id);
    }
}
