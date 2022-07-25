package com.ironhack.project.gameservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.project.gameservice.classes.Address;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private String  address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id")
    @JsonIgnore
    private User user;

    public Game() {
    }

    public Game(String date, String address, User user) {
        this.date = date;
        this.address = address;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }
}
