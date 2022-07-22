package com.ironhack.project.gameservice.models;

import javax.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String playerName;

/*    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;*/

/*    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private final Long role;*/

    public Player() {
        //this.role = 2L;
    }

    public Player(String name, String playerName, Game game) {
        this.name = name;
        this.playerName = playerName;
        //this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

/*    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }*/
}
