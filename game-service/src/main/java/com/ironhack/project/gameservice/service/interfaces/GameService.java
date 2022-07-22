package com.ironhack.project.gameservice.service.interfaces;

import com.ironhack.project.gameservice.models.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> FindAll();

    Game createGame(Game game);

    Game updateGame(Long id, Game game);

    Optional<Game> deleteGame(Long id);
}
