package com.ironhack.project.edgeservice.service.impl;

import com.ironhack.project.edgeservice.client.PlayerProxyClient;
import com.ironhack.project.edgeservice.models.Player;
import com.ironhack.project.edgeservice.service.interfaces.EdgePlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EdgePlayerServiceImpl implements EdgePlayerService {

    @Autowired
    private PlayerProxyClient playerProxyClient;

    public Player createPlayer(Player player) {

        return playerProxyClient.createPlayer(player);
    }
}
