package com.ironhack.project.edgeservice.controller.interfaces;

import com.ironhack.project.edgeservice.controller.dto.MemberDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberGetDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberPostDTO;
import com.ironhack.project.edgeservice.models.Member;

import java.util.List;

public interface EdgeMemberController {
    List<Member> findAll();
    MemberGetDTO getByEmail(String email);
    Member createMember(MemberPostDTO memberPostDTO);
    MemberDTO updateMember(Long id, MemberDTO memberDTO);
    void deleteMember(Long id);


}
