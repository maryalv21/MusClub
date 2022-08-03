package com.ironhack.project.gameservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.project.gameservice.models.Game;
import com.ironhack.project.gameservice.models.Role;
import com.ironhack.project.gameservice.models.User;
import com.ironhack.project.gameservice.repository.GameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GameRepository gameRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();


    private List<Game> gameList = new ArrayList<>();

    private Set<Role> roles = new HashSet<>();

    private Game game1;
    private User user1;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        game1 = new Game("12-03-2022", "La alegria", user1);
        game1.setId(1L);
        user1 = new User("Mafalda", "12345", game1, roles);

        gameRepository.save(game1);
    }

    @AfterEach
    void tearDown() {

        gameRepository.deleteAll();

    }

    @Test
    void findById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/games/"+game1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("La alegria"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("manoli"));
    }

    @Test
    void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/games/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("manoli"));
    }

    @Test
    void findGameByUserId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/usergame/"+ user1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("game1"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("manoli"));

    }

    @Test
    void createGame() throws Exception {
        Game game = new Game("tomorrow", "Bar tio pepe", user1);
        game.setId(1L);
        String body = objectMapper.writeValueAsString(game);
        MvcResult mvcResult = mockMvc.perform(post("/games/")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("manoli"));
    }

    @Test
    void updateGame() throws Exception {
        Game game = new Game("today", "Bar tio pepe", user1);
        game.setId(1L);
        String body = objectMapper.writeValueAsString(game);
        MvcResult mvcResult = mockMvc.perform(put("/games/"+game1.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("today"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("manoli"));
    }

    @Test
    void deleteGame() throws Exception {

        Game game = new Game("today", "Bar tio pepe", user1);
        game.setId(1L);
        MvcResult mvcResult = mockMvc.perform(delete("/games/"+game1.getId()))
                .andExpect(status().isAccepted())
                .andReturn();
        assertTrue(gameRepository.count() == 1);
    }
}