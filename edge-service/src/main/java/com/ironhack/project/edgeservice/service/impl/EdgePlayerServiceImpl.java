package com.ironhack.project.edgeservice.service.impl;

import com.ironhack.project.edgeservice.client.PlayerProxyClient;
import com.ironhack.project.edgeservice.models.Member;
import com.ironhack.project.edgeservice.models.Player;
import com.ironhack.project.edgeservice.service.interfaces.EdgePlayerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EdgePlayerServiceImpl implements EdgePlayerService {

    @Autowired
    private PlayerProxyClient playerProxyClient;

    private final Logger logger = LoggerFactory.getLogger(EdgeGameServiceImpl.class);

    @CircuitBreaker(name= "createPlayer", fallbackMethod= "createPlayerFallback")
    public Player createPlayer(Player player) {

        return playerProxyClient.createPlayer(player);
    }

    public Player createLPayerFallback(Exception e){
        logger.error(e.getMessage());
        throw new RuntimeException("Sorry serve not available, try later");
    }
}
