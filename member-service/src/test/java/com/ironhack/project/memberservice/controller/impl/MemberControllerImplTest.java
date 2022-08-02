package com.ironhack.project.memberservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.project.memberservice.controller.dto.MemberDTO;
import com.ironhack.project.memberservice.models.Member;
import com.ironhack.project.memberservice.models.Role;
import com.ironhack.project.memberservice.models.User;
import com.ironhack.project.memberservice.repository.MemberRepository;
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
class MemberControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Member> memberList;
    private User user1;
    private Member member1;

    private Set<Role> roles = new HashSet<>();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        memberList = new ArrayList<>();
        user1 = new User(1L, "MEMBER", "12345", null);
        member1 = new Member(1L, "mafalda", "12345", null, "mafalda",
                "mafalda","maf@maf.com", "beginner");

        memberRepository.save(member1);

    }

    @AfterEach
    void tearDown() {

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
        Member member = new Member(5L, "susanita", "12456",  roles,
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
        member1 = new Member(5L, "mafalda", "12345", null, "mafalda",
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

        member1 = new Member(1L, "mafalda", "12345", null, "mafalda",
                "mafalda","maf@maf.com", "beginner");
        MemberDTO memberDTO = new MemberDTO("Manolito");
        MvcResult mvcResult = mockMvc.perform(delete("/members/"+member1.getId()))
                .andExpect(status().isAccepted())
                .andReturn();
        assertTrue(memberRepository.count() == 0);
    }
}