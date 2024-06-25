package com.be.croffle.feign;

import com.be.croffle.member.oauth.dto.GoogleOAuthTokenRequest;
import com.be.croffle.member.oauth.dto.OAuthTokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "oauthFeignClient", url = "https://oauth2.googleapis.com",
        configuration = FeignClientConfig.class)
public interface OAuthFeignClient {
    @PostMapping("/token")
    GoogleOAuthTokenRequest getAccessToken(@RequestBody OAuthTokenRequest reqDto);


}