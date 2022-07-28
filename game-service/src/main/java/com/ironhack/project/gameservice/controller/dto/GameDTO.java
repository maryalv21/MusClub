package com.ironhack.project.gameservice.controller.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GameDTO {

    @Id
    @GeneratedValue
    private Long id;

    private String date;

    private String  address;

    private Long user_id;

    private String playerName2;

    private String playerName3;

    private String playerName4;

    public GameDTO() {
    }

    public GameDTO(String date, String address, Long user_id, String playerName2, String playerName3, String playerName4) {
        this.date = date;
        this.address = address;
        this.user_id = user_id;
        this.playerName2 = playerName2;
        this.playerName3 = playerName3;
        this.playerName4 = playerName4;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getPlayerName2() {
        return playerName2;
    }

    public void setPlayerName2(String playerName2) {
        this.playerName2 = playerName2;
    }

    public String getPlayerName3() {
        return playerName3;
    }

    public void setPlayerName3(String playerName3) {
        this.playerName3 = playerName3;
    }

    public String getPlayerName4() {
        return playerName4;
    }

    public void setPlayerName4(String playerName4) {
        this.playerName4 = playerName4;
    }
}
