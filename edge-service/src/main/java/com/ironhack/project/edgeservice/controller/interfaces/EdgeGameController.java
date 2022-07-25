package com.ironhack.project.edgeservice.controller.interfaces;

import com.ironhack.project.edgeservice.models.Game;

import java.util.List;
import java.util.Optional;

public interface EdgeGameController {

    List<Game> FindAll();

    Game findById(Long id);

    Game createGame(Game game);

    Game updateGame(Long id, Game game);

    void deleteGame(Long id);
}
