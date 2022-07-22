package com.ironhack.project.gameservice.models;

import com.ironhack.project.gameservice.classes.Address;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String date;

    @NotEmpty
    private String  address;
/*    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    @NotEmpty
    private Member member;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Player> playerList;*/

    public Game() {
    }

    public Game(String date, String  address, Member member) {
        this.date = date;
        this.address = address;
        //this.member = member;
        //this.playerList = new ArrayList<>(4);
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

    public String  getAddress() {
        return address;
    }

    public void setAddress(String  address) {
        this.address = address;
    }

/*    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }*/
}
