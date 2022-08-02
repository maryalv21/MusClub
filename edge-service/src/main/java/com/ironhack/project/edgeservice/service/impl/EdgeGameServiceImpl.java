package com.ironhack.project.edgeservice.service.impl;

import com.ironhack.project.edgeservice.client.GameProxyClient;
import com.ironhack.project.edgeservice.controller.dto.GameDTO;
import com.ironhack.project.edgeservice.models.Game;
import com.ironhack.project.edgeservice.models.User;
import com.ironhack.project.edgeservice.repository.GameRepository;
import com.ironhack.project.edgeservice.service.interfaces.EdgeGameService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class EdgeGameServiceImpl implements EdgeGameService {

    @Autowired
    private GameProxyClient gameProxyClient;

    @Autowired
    private GameRepository gameRepository;

    private final Logger logger = LoggerFactory.getLogger(EdgeGameServiceImpl.class);

    @CircuitBreaker(name= "findById", fallbackMethod= "findByIdFallback")
    public Game findById(Long id) {

        return gameProxyClient.findById(id);
    }

    public Game findByIdFallback(Long id, Exception e){
        logger.error(e.getMessage());

        Game game = new Game();
        game.setDate(game.getDate());
        game.setAddress(game.getAddress());
        game.setUser(game.getUser());
        return game;
    }

    @CircuitBreaker(name= "findAll", fallbackMethod= "findAllFallback")
    public List<Game> FindAll() {

        return gameProxyClient.FindAll();
    }

    public List<Game> findAllFallback(Exception e){
        logger.error(e.getMessage());
        Game game1 = new Game();
        User user1 = new User();
        List<Game> gameList = List.of((new Game("28-08-2022", "Bar Alegria", user1)));
        return gameList;
    }

    @CircuitBreaker(name= "findGameByUserId", fallbackMethod= "findGameByUserIdFallback")
    public List<GameDTO> findGameByUserId(Long id){

    return  gameProxyClient.findGameByUserId(id);
    }

    public List<GameDTO> findGameByUserIdFallback(Long id, Exception e){
        logger.error(e.getMessage());
        GameDTO game1DTO = new GameDTO();
        User user1 = new User();
        List<GameDTO> gameListDTO = List.of((new GameDTO("28-08-2022", "Bar Alegria", 0L,
                "susanita", "Felipe", "Pepito")));
        return gameListDTO;
    }

    @CircuitBreaker(name= "createGame", fallbackMethod= "createGameFallback")
    public Game createGame(Game game) {

        return gameProxyClient.createGame(game);
    }

    public Game createGameFallback(Game game, Exception e){
        logger.error(e.getMessage());
        throw new RuntimeException("Sorry serve not available, try later");
    }

    @CircuitBreaker(name= "updateGame", fallbackMethod= "updateGameFallback")
    public GameDTO updateGame(Long id, GameDTO gameDTO) {
        return gameProxyClient.updateGame(id, gameDTO);
    }


    public GameDTO updateGameFallback(Long id, GameDTO gameDTO, Exception e){
        logger.error(e.getMessage());
        throw new RuntimeException("Sorry serve not available, try later");
    }
    @CircuitBreaker(name= "deleteGame", fallbackMethod= "deleteGameFallback")
    public void deleteGame(Long id) {
        gameProxyClient.deleteGame(id);
    }


    public void deleteGameFallback(Long id, Exception e){
        logger.error(e.getMessage());
        throw new RuntimeException("Sorry serve not available, try later");
    }
}
