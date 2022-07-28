package com.ironhack.project.gameservice.service.impl;

import com.ironhack.project.gameservice.controller.dto.GameDTO;
import com.ironhack.project.gameservice.models.Game;
import com.ironhack.project.gameservice.repository.GameRepository;
import com.ironhack.project.gameservice.service.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game findById(Long id){
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (!optionalGame.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found");
        }
        Game game = new Game();
        game.setId(optionalGame.get().getId());
        game.setDate(optionalGame.get().getDate());
        game.setAddress(optionalGame.get().getAddress());
        game.setUser(optionalGame.get().getUser());
        return game;
    }

    public List<GameDTO> findGameByUserId(Long id) {
        List<Game> gameList = gameRepository.findGameByUserId(id);
        List<GameDTO> gameDTOList = new ArrayList<>();
        GameDTO gameDTO = new GameDTO();
        for(int i=0; i < gameList.size(); i++){
            gameDTO.setUser_id(gameList.get(i).getUser().getId());
            gameDTO.setAddress(gameList.get(i).getAddress());
            gameDTO.setDate(gameList.get(i).getDate());
            gameDTO.setId(gameList.get(i).getId());
            gameDTO.setPlayerName2(gameList.get(i).getUser().getUsername());
            gameDTOList.add(gameDTO);
        }
        return gameDTOList;
    }

    @Override
    public List<Game> FindAll() {
        List<Game> gameList = gameRepository.findAll();
        return gameList;
    }

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    //Revisar
    @Override
    public GameDTO updateGame(Long id, GameDTO gameDTO) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if(!optionalGame.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        optionalGame.get().setDate(gameDTO.getDate());
        optionalGame.get().setAddress(gameDTO.getAddress());
        gameRepository.save(optionalGame.get());
        return gameDTO;
    }

    @Override
    public void deleteGame(Long id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if(!optionalGame.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        Game game = new Game();
        game.setId(optionalGame.get().getId());
        gameRepository.delete(game);
    }
}
