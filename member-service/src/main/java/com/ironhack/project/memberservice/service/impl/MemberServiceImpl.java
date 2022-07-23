package com.ironhack.project.memberservice.service.impl;

import com.ironhack.project.memberservice.controller.dto.MemberDTO;
import com.ironhack.project.memberservice.controller.dto.MemberGetDTO;
import com.ironhack.project.memberservice.controller.dto.MemberPostDTO;
import com.ironhack.project.memberservice.models.Member;
import com.ironhack.project.memberservice.repository.MemberRepository;
import com.ironhack.project.memberservice.service.interfaces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberGetDTO getByEmail(String email) {
        Optional<Member> optionalMember = memberRepository.getByEmail(email);
        if (!optionalMember.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found");
        }
        MemberGetDTO memberGetDTO = new MemberGetDTO();
        memberGetDTO.setName(optionalMember.get().getName());
        memberGetDTO.setPlayerName(optionalMember.get().getPlayerName());
        memberGetDTO.setEmail(optionalMember.get().getEmail());
        memberGetDTO.setLevel(optionalMember.get().getLevel());
        return memberGetDTO;
    }


    @Override
    public List<Member> findAll() {
    List<Member> memberList = memberRepository.findAll();
        return memberList;
    }

    @Override
    public Member createMember(MemberPostDTO memberPostDTO) {
        Member member = new Member();
        member.setName(memberPostDTO.getName());
        member.setEmail(memberPostDTO.getEmail());
        member.setLevel(memberPostDTO.getLevel());
        member.setPlayerName(memberPostDTO.getPlayerName());
        member.setUsername(memberPostDTO.getUsername());
        member.setPassword(memberPostDTO.getPassword());
        member.setRoles(memberPostDTO.getRoles());
        return memberRepository.save(member);
    }

    @Override
    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (!optionalMember.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found");
        }
        memberDTO.setPlayerName(optionalMember.get().getPlayerName());
        memberRepository.save(optionalMember.get());
        return memberDTO;
    }

    @Override
    public void deleteMember(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        Member member = new Member();
        member.setId(optionalMember.get().getId());
        memberRepository.delete(member);
    }
}
