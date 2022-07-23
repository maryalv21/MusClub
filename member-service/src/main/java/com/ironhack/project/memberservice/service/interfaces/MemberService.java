package com.ironhack.project.memberservice.service.interfaces;

import com.ironhack.project.memberservice.controller.dto.MemberDTO;
import com.ironhack.project.memberservice.controller.dto.MemberGetDTO;
import com.ironhack.project.memberservice.controller.dto.MemberPostDTO;
import com.ironhack.project.memberservice.models.Member;

import java.util.List;
import java.util.Optional;


public interface MemberService {

    List<Member> findAll();
    MemberGetDTO getByEmail(String email);
    Member createMember(MemberPostDTO memberPostDTO);
    public MemberDTO updateMember(Long id, MemberDTO memberDTO);
    public void deleteMember(Long id);
}
