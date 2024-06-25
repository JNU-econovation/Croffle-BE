package com.be.croffle.member;

import com.be.croffle.common.TokenGenerator;
import com.be.croffle.member.oauth.dto.OAuthUserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenGenerator tokenGenerator;

    public String findMemberByGoogleId(OAuthUserInfoResponse userInfoResponse) {
        Member member = memberRepository.findByGoogleId(userInfoResponse.getId()).orElseGet(
                ()->
                memberRepository.save(Member.builder()
                        .googleId(userInfoResponse.getId())
                        .name(userInfoResponse.getName())
                        .build()));

        //accessToken 생성
        return tokenGenerator.createAccessToken(member);

    }
}
