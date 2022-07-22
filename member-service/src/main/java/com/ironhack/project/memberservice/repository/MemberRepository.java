package com.ironhack.project.memberservice.repository;

import com.ironhack.project.memberservice.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAll();
    Optional<Member> findById(Long id);


}
