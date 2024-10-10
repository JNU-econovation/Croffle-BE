package com.be.croffle.member.oauth.service;

import com.be.croffle.feign.oauth.GoogleFeignClient;
import com.be.croffle.member.oauth.dto.response.OAuthUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthUserInfoService {

    private final GoogleFeignClient googleFeignClient;

    public OAuthUserInfoResponse getUserInfo(String token){
        return googleFeignClient.getUserInfo(token);
    }
}