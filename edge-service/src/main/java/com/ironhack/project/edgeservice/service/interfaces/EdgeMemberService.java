package com.ironhack.project.edgeservice.service.interfaces;

import com.ironhack.project.edgeservice.controller.dto.MemberDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberGetDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberPostDTO;
import com.ironhack.project.edgeservice.models.Member;

import java.util.List;

public interface EdgeMemberService {

    List<Member> findAll();

    MemberGetDTO getById(Long id);

    Member createMember(MemberPostDTO memberPostDTO);

    MemberDTO updateMember(Long id, MemberDTO memberDTO);

    void deleteMember(Long id);
}
