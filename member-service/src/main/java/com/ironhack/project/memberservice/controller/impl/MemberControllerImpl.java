package com.ironhack.project.memberservice.controller.impl;

import com.ironhack.project.memberservice.controller.dto.MemberDTO;
import com.ironhack.project.memberservice.controller.dto.MemberGetDTO;
import com.ironhack.project.memberservice.controller.dto.MemberPostDTO;
import com.ironhack.project.memberservice.controller.interfaces.MemberController;
import com.ironhack.project.memberservice.models.Member;
import com.ironhack.project.memberservice.repository.MemberRepository;
import com.ironhack.project.memberservice.service.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberControllerImpl implements MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    @ResponseStatus(HttpStatus.OK)
    public List<Member> findAll() {
        List<Member> memberList = memberService.findAll();
        return memberList;
    }

/*    @GetMapping("/members/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberGetDTO getById(@PathVariable @Validated Long id) {
        MemberGetDTO memberGetDTO = memberService.getById(id);
        return memberGetDTO;
    }*/

    @GetMapping("/members/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Member login(@PathVariable String username) {
        Member member = memberService.login(username);
        return member;
    }

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public Member createMember(@RequestBody @Validated MemberPostDTO memberPostDTO) {
        Member member = memberService.createMember(memberPostDTO);
        return memberRepository.save(member);
    }

    //No funciona metodo path en el edge-service
    //@PatchMapping(path="/members/{id}", consumes = "application/json-patch+json")
    @PutMapping("/members/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MemberDTO updateMember(@PathVariable @Validated Long id, @RequestBody MemberDTO memberDTO) {
        memberService.updateMember(id, memberDTO);
        return memberDTO;
    }

    @DeleteMapping("/members/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteMember(@PathVariable @Validated Long id) {
        memberService.deleteMember(id);
    }
}
