package com.ironhack.project.memberservice.controller.dto;

import javax.validation.constraints.NotEmpty;

public class MemberGetDTO {

    @NotEmpty
    private String name;
    @NotEmpty
    private String playerName;
    @NotEmpty
    private String email;

    private String level;

    public MemberGetDTO() {
    }

    public MemberGetDTO(String name, String playerName, String email, String level) {
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
