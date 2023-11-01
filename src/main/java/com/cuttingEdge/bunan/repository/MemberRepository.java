package com.cuttingEdge.bunan.repository;

import com.cuttingEdge.bunan.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> existsMemberByNickname(String nickname);
    Optional<Member> existsMemberByEmail(String email);

}
