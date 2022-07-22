package com.ironhack.project.gameservice.repository;

import com.ironhack.project.gameservice.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository  extends JpaRepository<Game, Long> {

}
