package com.ironhack.project.edgeservice.service.interfaces;

import com.ironhack.project.edgeservice.models.Game;

import java.util.List;
import java.util.Optional;

public interface EdgeGameService {

    List<Game> FindAll();

    Game createGame(Game game);

    Game updateGame(Long id, Game game);

    Optional<Game> deleteGame(Long id);
}
