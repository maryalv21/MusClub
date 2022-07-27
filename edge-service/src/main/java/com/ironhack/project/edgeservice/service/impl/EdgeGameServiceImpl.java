package com.ironhack.project.edgeservice.service.impl;

import com.ironhack.project.edgeservice.client.GameProxyClient;
import com.ironhack.project.edgeservice.models.Game;
import com.ironhack.project.edgeservice.repository.GameRepository;
import com.ironhack.project.edgeservice.service.interfaces.EdgeGameService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdgeGameServiceImpl implements EdgeGameService {

    @Autowired
    private GameProxyClient gameProxyClient;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game findById(Long id) {
        return gameProxyClient.findById(id);
    }

    @Override
    public List<Game> FindAll() {

        return gameProxyClient.FindAll();
    }

    @Override
    public Game createGame(Game game) {
        return gameProxyClient.createGame(game);
    }

    @Override
    public Game updateGame(Long id, Game game) {
        return gameProxyClient.updateGame(id, game);
    }

    @Override
    public void deleteGame(Long id) {
        gameProxyClient.deleteGame(id);
    }
}
