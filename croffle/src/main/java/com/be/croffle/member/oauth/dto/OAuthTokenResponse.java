package com.be.croffle.member.oauth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthTokenResponse {
    public String accessToken;
}
