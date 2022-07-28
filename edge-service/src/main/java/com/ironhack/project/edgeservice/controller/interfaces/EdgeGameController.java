package com.ironhack.project.edgeservice.controller.interfaces;

import com.ironhack.project.edgeservice.controller.dto.GameDTO;
import com.ironhack.project.edgeservice.models.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface EdgeGameController {

    List<Game> FindAll();

    Game findById(Long id);


    List<GameDTO> findGameByUserId(Long id);

    Game createGame(Game game);

    GameDTO updateGame(Long id, GameDTO gameDTO);

    void deleteGame(Long id);
}
