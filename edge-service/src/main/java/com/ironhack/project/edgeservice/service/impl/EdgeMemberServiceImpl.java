package com.ironhack.project.edgeservice.service.impl;

import com.ironhack.project.edgeservice.client.MemberProxyClient;
import com.ironhack.project.edgeservice.controller.dto.MemberDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberGetDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberPostDTO;
import com.ironhack.project.edgeservice.models.Member;
import com.ironhack.project.edgeservice.service.interfaces.EdgeMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdgeMemberServiceImpl implements EdgeMemberService {

    @Autowired
    private MemberProxyClient memberProxyClient;

    @Override
    public MemberGetDTO getById(Long id) {
        return memberProxyClient.getById(id);
    }



    @Override
    public List<Member> findAll() {
        List<Member> memberList = memberProxyClient.findAll();
        return memberList;
    }

    @Override
    public Member createMember(MemberPostDTO memberPostDTO) {
        Member member = memberProxyClient.createMember(memberPostDTO);
        return member;
    }

    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        memberProxyClient.updateMember(id, memberDTO);
        return memberDTO;
    }

    @Override
    public void deleteMember(Long id) {
        memberProxyClient.deleteMember(id);
    }

}
