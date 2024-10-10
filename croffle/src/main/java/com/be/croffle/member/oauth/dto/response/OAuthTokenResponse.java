package com.be.croffle.member.oauth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthTokenResponse {
    public String accessToken;
}
