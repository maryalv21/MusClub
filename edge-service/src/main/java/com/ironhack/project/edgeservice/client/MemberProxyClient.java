package com.ironhack.project.edgeservice.client;

import com.ironhack.project.edgeservice.controller.dto.MemberDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberGetDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberPostDTO;
import com.ironhack.project.edgeservice.models.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("member-service")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface MemberProxyClient {

    @GetMapping("/members")
    List<Member> findAll();

/*    @GetMapping("/members/{id}")
    @ResponseStatus(HttpStatus.OK)
    MemberGetDTO getById(@PathVariable Long id);*/

    @GetMapping("/members/{username}")
    @ResponseStatus(HttpStatus.OK)
    Member login(@PathVariable String username);

    @PostMapping("/members")
    Member createMember(@RequestBody MemberPostDTO memberPostDTO);

    //No funciona metodo path, intente esto para resolverlo pero ni aun asi
    //@PatchMapping(path="/members/{id}", consumes = "application/json-patch+json")
    @PutMapping("/members/{id}")
    MemberDTO updateMember(@PathVariable Long id, @RequestBody MemberDTO memberDTO);

    @DeleteMapping("/members/{id}")
    void deleteMember(@PathVariable Long id);
}
