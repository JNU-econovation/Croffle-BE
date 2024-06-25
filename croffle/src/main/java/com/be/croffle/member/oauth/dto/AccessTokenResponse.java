package com.be.croffle.member.oauth.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
public class AccessTokenResponse {
    private String accessToken;
}
