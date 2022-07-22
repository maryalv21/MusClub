package com.ironhack.project.gameservice.service.impl;

import com.ironhack.project.gameservice.models.Game;
import com.ironhack.project.gameservice.repository.GameRepository;
import com.ironhack.project.gameservice.service.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> FindAll() {
        List<Game> gameList = gameRepository.findAll();
        return gameList;
    }

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(Long id, Game game) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if(!optionalGame.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        optionalGame.get().setId(game.getId());
        optionalGame.get().setDate(game.getDate());
        optionalGame.get().setAddress(game.getAddress());
        gameRepository.save(optionalGame.get());
        return game;
    }


    @Override
    public Optional<Game> deleteGame(Long id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if(!optionalGame.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        gameRepository.delete(optionalGame.get());
        return optionalGame;
    }
}
