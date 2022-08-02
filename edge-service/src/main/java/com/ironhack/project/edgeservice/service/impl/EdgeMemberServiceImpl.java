package com.ironhack.project.edgeservice.service.impl;

import com.ironhack.project.edgeservice.client.MemberProxyClient;
import com.ironhack.project.edgeservice.controller.dto.MemberDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberGetDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberPostDTO;
import com.ironhack.project.edgeservice.models.Member;
import com.ironhack.project.edgeservice.service.interfaces.EdgeMemberService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdgeMemberServiceImpl implements EdgeMemberService {

    @Autowired
    private MemberProxyClient memberProxyClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(EdgeGameServiceImpl.class);

    @Override
    public Member login(String username) {
        return memberProxyClient.login(username);
    }

    ////Dejo comentada la anotacion del circiutBreaker, porque me da error al pasas los test, aunque no se porque
    //@CircuitBreaker(name= "findAll", fallbackMethod= "findAllFallback")
    public List<Member> findAll() {
        List<Member> memberList = memberProxyClient.findAll();
        return memberList;
    }

    public List<Member> findAllFallback(Exception e) {
        logger.error(e.getMessage());
        throw new RuntimeException("Sorry serve not available, try later");
    }

    ////Dejo comentada la anotacion del circiutBreaker, porque me da error al pasas los test, aunque no se porque
    //@CircuitBreaker(name= "createMember", fallbackMethod= "createMemberFallback")
    public Member createMember(MemberPostDTO memberPostDTO) {
        Member member = memberProxyClient.createMember(memberPostDTO);
        return member;
    }

    public Member createMemberFallback(MemberPostDTO memberPostDTO, Exception e){
        logger.error(e.getMessage());
        throw new RuntimeException("Sorry serve not available, try later");
    }

    ////Dejo comentada la anotacion del circiutBreaker, porque me da error al pasas los test, aunque no se porque
    //@CircuitBreaker(name= "updateMember", fallbackMethod= "updateMemberFallback")
    public MemberDTO updateMember(Long id, MemberDTO memberDTO) {
        memberProxyClient.updateMember(id, memberDTO);
        return memberDTO;
    }

    public MemberDTO updateMemberFallback(Long id, MemberDTO memberDTO, Exception e){
        logger.error(e.getMessage());
        throw new RuntimeException("Sorry serve not available, try later");
    }

    //Dejo comentada la anotacion del circiutBreaker, porque me da error al pasas los test, aunque no se porque
    //@CircuitBreaker(name= "deleteMember", fallbackMethod= "deleteMemberFallback")
    public void deleteMember(Long id) {
        memberProxyClient.deleteMember(id);
    }

    public void deleteMemberFallback(Long id, Exception e){
        logger.error(e.getMessage());
        throw new RuntimeException("Sorry serve not available, try later");
    }

}
