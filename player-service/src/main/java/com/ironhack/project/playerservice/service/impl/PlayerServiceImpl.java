package com.ironhack.project.playerservice.service.impl;

import com.ironhack.project.playerservice.models.Player;
import com.ironhack.project.playerservice.repository.PlayerRepository;
import com.ironhack.project.playerservice.service.interfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl  implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }
}
