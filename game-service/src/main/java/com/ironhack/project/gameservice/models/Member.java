package com.ironhack.project.gameservice.models;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@OneToOne(mappedBy = "member")
    private Long id;
    private String name;
    private String playerName;
    private String email;
    private String level;
    private String password;

/*    @OneToOne(mappedBy = "game")
    private Game game;*/

 /*   @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private final Long role;*/

    public Member() {
        //this.role = 2L;
    }

    public Member(String name, String playerName, String email, String level, String password) {
        this.name = name;
        this.playerName = playerName;
        this.email = email;
        this.level = level;
        this.password = password;
        //this.role = 1L;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /*    public Long getRole() {
        return role;
    }*/
}
