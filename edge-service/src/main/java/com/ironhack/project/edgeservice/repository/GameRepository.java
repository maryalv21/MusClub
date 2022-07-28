package com.ironhack.project.edgeservice.repository;

import com.ironhack.project.edgeservice.controller.dto.GameDTO;
import com.ironhack.project.edgeservice.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<GameDTO> findGameByUserId(Long id);

}
