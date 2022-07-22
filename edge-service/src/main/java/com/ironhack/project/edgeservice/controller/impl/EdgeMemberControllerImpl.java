package com.ironhack.project.edgeservice.controller.impl;

import com.ironhack.project.edgeservice.client.MemberProxyClient;
import com.ironhack.project.edgeservice.controller.dto.MemberDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberGetDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberPostDTO;
import com.ironhack.project.edgeservice.controller.interfaces.EdgeMemberController;
import com.ironhack.project.edgeservice.models.Member;
import com.ironhack.project.edgeservice.service.interfaces.EdgeMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EdgeMemberControllerImpl implements EdgeMemberController {

    @Autowired
    private EdgeMemberService edgeMemberService;

    @Autowired
    private MemberProxyClient memberProxyClient;


    @GetMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    public List<Member> findAll() {
        return edgeMemberService.findAll();
    }

    @GetMapping("/members/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberGetDTO getById(@PathVariable Long id) {
        MemberGetDTO memberGetDTO = edgeMemberService.getById(id);
        return memberGetDTO;
    }

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public Member createMember(@RequestBody MemberPostDTO memberPostDTO) {
        Member member = edgeMemberService.createMember(memberPostDTO);
        return member;
    }
    //No funciona metodo path, intente esto para resolverlo pero ni aun asi
    //@PatchMapping(path="/members/{id}", consumes = "application/json-patch+json")
    @PutMapping("/members/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MemberDTO updateMember(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {
        edgeMemberService.updateMember(id, memberDTO);
        return memberDTO;
    }

    @DeleteMapping("/members/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteMember(@PathVariable Long id) {
        edgeMemberService.deleteMember(id);
    }

}