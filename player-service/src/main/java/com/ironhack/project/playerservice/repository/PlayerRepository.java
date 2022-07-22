package com.ironhack.project.playerservice.repository;

import com.ironhack.project.playerservice.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    //List<Member> findAll();
    Optional<Player> findById(Long id);

}
