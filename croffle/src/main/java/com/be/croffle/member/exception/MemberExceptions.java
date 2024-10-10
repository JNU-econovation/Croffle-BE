package com.be.croffle.member.exception;

import com.be.croffle.common.security.UserDetailsImpl;
import com.be.croffle.member.domain.Member;
import com.be.croffle.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberExceptions {
    private final MemberJpaRepository memberJpaRepository;


    public Member findByGoogleId(UserDetailsImpl userDetails){
       return memberJpaRepository.findByGoogleId(userDetails.getGoogleId())
                .orElseThrow(() -> new MemberNotExistException(MemberErrorMessage.MEMBER_NOT_EXIST));
    }
}
