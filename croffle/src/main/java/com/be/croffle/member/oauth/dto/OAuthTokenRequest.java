package com.be.croffle.member.oauth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthTokenRequest {
    String code;
    String client_id;
    String client_secret;
    String redirect_uri;
    String grant_type;
}
