package com.ironhack.project.edgeservice.client;

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

    @PostMapping("/games")
    Game createGame(@RequestBody Game game);

    @PutMapping("/games/{id}")
    Game updateGame(@PathVariable Long id, @RequestBody Game game);

    @DeleteMapping("/games/{id}")
    Optional<Game> deleteGame(@PathVariable Long id);
}
