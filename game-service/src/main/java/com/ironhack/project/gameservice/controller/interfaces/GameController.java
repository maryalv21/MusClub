package com.ironhack.project.gameservice.controller.interfaces;

import com.ironhack.project.gameservice.models.Game;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

public interface GameController {

    List<Game> FindAll();

    Game createGame(Game game);

    Game updateGame(Long id, Game game);

    Optional<Game> deleteGame(Long id);
}
