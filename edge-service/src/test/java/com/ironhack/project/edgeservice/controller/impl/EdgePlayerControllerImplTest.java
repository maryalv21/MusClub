package com.ironhack.project.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.project.edgeservice.models.Player;
import com.ironhack.project.edgeservice.repository.GameRepository;
import com.ironhack.project.edgeservice.repository.PlayerRepository;
import com.ironhack.project.edgeservice.service.interfaces.EdgePlayerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EdgePlayerControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private EdgePlayerService edgePlayerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    Player player = new Player();

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        player = new Player("Invite", "invite");
        playerRepository.save(player);

    }

    @AfterEach
    void tearDown(){
        playerRepository.deleteAll();
    }

    @Test
    void createPlayer() throws Exception {
        String body = objectMapper.writeValueAsString(player);

        MvcResult mvcResult = mockMvc.perform(post("/players")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Invite"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("other"));

    }
}