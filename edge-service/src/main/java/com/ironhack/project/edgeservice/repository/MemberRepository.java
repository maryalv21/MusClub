package com.ironhack.project.edgeservice.repository;

import com.ironhack.project.edgeservice.models.Member;
import com.ironhack.project.edgeservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<User> findByUsername(String username);
}
