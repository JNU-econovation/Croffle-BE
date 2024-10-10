package com.be.croffle.member.oauth.service;

import com.be.croffle.feign.oauth.OAuthFeignClient;
import com.be.croffle.member.service.MemberService;
import com.be.croffle.member.oauth.dto.response.AccessTokenResponse;
import com.be.croffle.member.oauth.dto.request.GoogleOAuthTokenRequest;
import com.be.croffle.member.oauth.dto.request.OAuthTokenRequest;
import com.be.croffle.member.oauth.dto.response.OAuthUserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuthTokenService {

    @Value("${oauth2.google.client-id}")
    private String GOOGLE_CLIENT_ID;
    @Value("${oauth2.google.client-secret}")
    private String GOOGLE_CLIENT_SECRET;
    @Value("${oauth2.google.redirect-uri}")
    private String LOGIN_REDIRECT_URL;
    @Value("${oauth2.google.grant-type}")
    private String GRANT_TYPE;


    private final OAuthFeignClient oAuthFeignClient;
    private final OAuthUserInfoService oAuthUserInfoService;
    private final MemberService memberService;

    public AccessTokenResponse getGoogleAccessToken(String code) {
        OAuthTokenRequest oAuthTokenRequest = OAuthTokenRequest.builder()
                .code(code)
                .client_id(GOOGLE_CLIENT_ID)
                .client_secret(GOOGLE_CLIENT_SECRET)
                .redirect_uri(LOGIN_REDIRECT_URL)
                .grant_type(GRANT_TYPE)
                .build();

       GoogleOAuthTokenRequest googleOAuthTokenRequest = oAuthFeignClient.getAccessToken(oAuthTokenRequest);
       OAuthUserInfoResponse userInfo = oAuthUserInfoService.getUserInfo(googleOAuthTokenRequest.getAccess_token());
       String token = memberService.findMemberByGoogleId(userInfo);

        return AccessTokenResponse.builder()
                .accessToken(token)
                .build();
    }
}