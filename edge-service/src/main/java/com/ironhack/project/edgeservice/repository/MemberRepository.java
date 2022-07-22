package com.ironhack.project.edgeservice.repository;

import com.ironhack.project.edgeservice.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
