package com.ironhack.project.edgeservice.controller.dto;

public class MemberDTO {

    private String playerName;

    public MemberDTO() {
    }

    public MemberDTO(String playerName) {

        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
