package com.be.croffle.member;

import com.be.croffle.common.security.TokenGenerator;
import com.be.croffle.member.oauth.dto.OAuthUserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberJpaRepository memberJpaRepository;
    private final TokenGenerator tokenGenerator;

    public String findMemberByGoogleId(OAuthUserInfoResponse userInfoResponse) {
        Member member = memberJpaRepository.findByGoogleId(userInfoResponse.getId()).orElseGet(
                ()->
                memberJpaRepository.save(Member.builder()
                        .googleId(userInfoResponse.getId())
                        .name(userInfoResponse.getName())
                        .build()));

        //accessToken 생성
        return tokenGenerator.createAccessToken(member);

    }
}
