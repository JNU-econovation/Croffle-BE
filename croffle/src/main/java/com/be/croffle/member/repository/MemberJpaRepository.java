package com.be.croffle.member.repository;


import com.be.croffle.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByGoogleId(String googleId);
}
