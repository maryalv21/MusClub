package com.ironhack.project.gameservice.repository;

import com.ironhack.project.gameservice.controller.dto.GameDTO;
import com.ironhack.project.gameservice.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository  extends JpaRepository<Game, Long> {

    List<Game> findAll();

    Optional<Game> findById(Long id);

    @Query(value="select g.id, g.address, g.date, g.user_id from musclub.game as g\n" +
            "            inner join serurity_project.user as u\n" +
            "            on g.user_id = u.id where u.id = ?; ",nativeQuery=true)
    List<Game> findGameByUserId(Long id);

}
