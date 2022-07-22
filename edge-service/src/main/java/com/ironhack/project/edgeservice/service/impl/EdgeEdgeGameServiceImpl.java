package com.ironhack.project.edgeservice.service.impl;

import com.ironhack.project.edgeservice.client.GameProxyClient;
import com.ironhack.project.edgeservice.models.Game;
import com.ironhack.project.edgeservice.service.interfaces.EdgeGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdgeEdgeGameServiceImpl implements EdgeGameService {

    @Autowired
    private GameProxyClient proxyClient;
    @Override
    public List<Game> FindAll() {
        return proxyClient.FindAll();
    }

    @Override
    public Game createGame(Game game) {
        return proxyClient.createGame(game);
    }

    @Override
    public Game updateGame(Long id, Game game) {
        return proxyClient.updateGame(id, game);
    }

    @Override
    public Optional<Game> deleteGame(Long id) {
        return proxyClient.deleteGame(id);
    }
}
