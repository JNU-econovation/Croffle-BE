package com.be.croffle.member.oauth.dto;

import lombok.Getter;

@Getter
public class OAuthUserInfoResponse {
    private String id;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String locale;
}
