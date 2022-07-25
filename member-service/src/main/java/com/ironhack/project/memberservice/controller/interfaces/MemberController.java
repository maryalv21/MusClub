package com.ironhack.project.memberservice.controller.interfaces;

import com.ironhack.project.memberservice.controller.dto.MemberDTO;
import com.ironhack.project.memberservice.controller.dto.MemberGetDTO;
import com.ironhack.project.memberservice.controller.dto.MemberPostDTO;
import com.ironhack.project.memberservice.models.Member;

import java.util.List;
import java.util.Optional;


public interface MemberController {
    List<Member> findAll();
    //MemberGetDTO getById(Long id);
    Member login(String username);
    Member createMember(MemberPostDTO memberPostDTO);
    MemberDTO updateMember(Long id, MemberDTO memberDTO);
    void deleteMember(Long id);
}
