package com.ironhack.project.memberservice.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Member extends User{
    private String name;
    private String playerName;
    private String email;
    private String level;

    public Member() {
    }

    public Member(Long id, String username, String password, Set<Role> roles, String name,
                  String playerName, String email, String level) {
        super(id, username, password, roles);
        this.name = name;
        this.playerName = playerName;
        this.email = email;
        this.level = level;
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
}
