package com.ironhack.project.edgeservice.client;

import com.ironhack.project.edgeservice.controller.dto.GameDTO;
import com.ironhack.project.edgeservice.models.Game;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("game-service")
public interface GameProxyClient {

    @GetMapping("/games")
    List<Game> FindAll();

    @GetMapping("/games/{id}")
    Game findById(@PathVariable Long id);

    @GetMapping("/usergame/{id}")
    public List<GameDTO> findGameByUserId(@PathVariable (name="id") Long id);

    @PostMapping("/games")
    Game createGame(@RequestBody Game game);

    @PutMapping("/games/{id}")
    GameDTO updateGame(@PathVariable Long id, @RequestBody GameDTO gameDTO);

    @DeleteMapping("/games/{id}")
    Optional<Game> deleteGame(@PathVariable Long id);
}
