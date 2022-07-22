package com.ironhack.project.memberservice.controller.dto;

import com.ironhack.project.memberservice.models.Role;

import java.util.Set;

public class MemberPostDTO {
    private String username;
    private String password;
    private String name;
    private String playerName;
    private String email;
    private String level;

    private Set<Role> roles;

    public MemberPostDTO() {
    }

    public MemberPostDTO(String username, String password, String name,
                         String playerName, String email, String level, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.playerName = playerName;
        this.email = email;
        this.level = level;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<Role> getRoles() {
        return roles;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
