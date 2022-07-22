package com.ironhack.project.memberservice.controller.dto;

import javax.validation.constraints.NotEmpty;

public class MemberDTO {
    @NotEmpty
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
