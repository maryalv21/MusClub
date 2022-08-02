package com.ironhack.project.edgeservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.project.edgeservice.controller.dto.MemberDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.AssertTrue;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EdgeMemberControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Member> memberList;

    private Game game1;
    private User user1;
    private Member member1;

    private Set<Role> roles = new HashSet<>();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        memberList = new ArrayList<>();
        game1 = new Game("12-03-2022", "La alegria", user1);
        user1 = new User(1L, "MEMBER", "12345", null, null);
        member1 = new Member(1L, "mafalda", "12345", null, null, "mafalda",
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
    void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/members/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("mafalda"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("manoli"));

    }

    @Test
    void createMember() throws Exception {
        Member member = new Member(5L, "susanita", "12456", null, roles,
                "mafalda", "susanita","maf@maf.com", "beginner");
        String body = objectMapper.writeValueAsString(member);
        MvcResult mvcResult = mockMvc.perform(post("/members/")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("susanita"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("manoli"));
    }

    @Test
    void updateMember() throws Exception {
        member1 = new Member(5L, "mafalda", "12345", null, null, "mafalda",
                "mafalda","maf@maf.com", "beginner");
        MemberDTO memberDTO = new MemberDTO("Manolito");
        String body = objectMapper.writeValueAsString(memberDTO);
        MvcResult mvcResult = mockMvc.perform(put("/members/"+member1.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Manolito"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("manoli"));

    }

    @Test
    void deleteMember() throws Exception {

        member1 = new Member(1L, "mafalda", "12345", null, null, "mafalda",
                "mafalda","maf@maf.com", "beginner");
        MemberDTO memberDTO = new MemberDTO("Manolito");
        MvcResult mvcResult = mockMvc.perform(delete("/members/"+member1.getId()))
                .andExpect(status().isAccepted())
                .andReturn();
        assertTrue(memberRepository.count() == 1);
    }
}