package com.be.croffle.member.oauth.dto;


import lombok.Getter;

@Getter
public class GoogleOAuthTokenRequest {
    public String access_token;
    public String expires_in;
    public String refresh_token;
    public String scope;
    public String token_type;
    public String id_token;
}