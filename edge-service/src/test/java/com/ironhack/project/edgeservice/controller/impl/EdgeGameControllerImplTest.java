package com.ironhack.project.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.project.edgeservice.client.GameProxyClient;
import com.ironhack.project.edgeservice.client.MemberProxyClient;
import com.ironhack.project.edgeservice.models.Game;
import com.ironhack.project.edgeservice.models.Member;
import com.ironhack.project.edgeservice.models.Role;
import com.ironhack.project.edgeservice.models.User;
import com.ironhack.project.edgeservice.repository.GameRepository;
import com.ironhack.project.edgeservice.repository.MemberRepository;
import com.ironhack.project.edgeservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class EdgeGameControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ApplicationContext context;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private GameProxyClient gameProxyClient;

    @MockBean
    private MemberProxyClient memberProxyClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Member> memberList;

    private List<Game> gameList = new ArrayList<>();

    private Set<Role> roles = new HashSet<>();

    private Game game1;
    private User user1;
    private Member member1;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        memberList = new ArrayList<>();
        game1 = new Game("12-03-2022", "La alegria", user1);
        game1.setId(1L);
        user1 = new User(1L, "MEMBER", "12345", game1, null);
        member1 = new Member(1L, "mafalda", "12345", game1, null, "mafalda",
                "mafalda","maf@maf.com", "beginner");

        gameRepository.save(game1);
        userRepository.save(user1);
        memberRepository.save(member1);

    }

    @AfterEach
    void tearDown() {

        gameRepository.deleteAll();
        userRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void findById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/games/"+ game1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1"));
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
        assertTrue(gameRepository.count() == 1);
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