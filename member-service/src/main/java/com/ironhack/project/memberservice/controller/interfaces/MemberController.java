package com.ironhack.project.memberservice.controller.interfaces;

import com.ironhack.project.memberservice.controller.dto.MemberDTO;
import com.ironhack.project.memberservice.controller.dto.MemberGetDTO;
import com.ironhack.project.memberservice.controller.dto.MemberPostDTO;
import com.ironhack.project.memberservice.models.Member;

import java.util.List;


public interface MemberController {
    List<Member> findAll();
    MemberGetDTO getByEmail(String email);
    Member createMember(MemberPostDTO memberPostDTO);
    MemberDTO updateMember(Long id, MemberDTO memberDTO);
    void deleteMember(Long id);
}
