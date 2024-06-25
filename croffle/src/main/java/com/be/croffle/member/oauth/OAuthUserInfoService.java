package com.be.croffle.member.oauth;

import com.be.croffle.feign.GoogleFeignClient;
import com.be.croffle.member.oauth.dto.OAuthUserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthUserInfoService {

    private final GoogleFeignClient googleFeignClient;

    public OAuthUserInfoResponse getUserInfo(String token){
        return googleFeignClient.getUserInfo(token);
    }
}