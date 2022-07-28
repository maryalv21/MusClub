package com.ironhack.project.gameservice.service.interfaces;

import com.ironhack.project.gameservice.controller.dto.GameDTO;
import com.ironhack.project.gameservice.models.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> FindAll();

    Game findById(Long id);

    List<GameDTO> findGameByUserId(Long id);

    Game createGame(Game game);

    GameDTO updateGame(Long id, GameDTO gameDTO);

    void deleteGame(Long id);
}
