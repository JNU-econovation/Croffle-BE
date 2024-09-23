package com.be.croffle.feign.oauth;

import com.be.croffle.feign.common.FeignClientConfig;
import com.be.croffle.member.oauth.dto.response.OAuthUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googleFeignClient", url = "https://www.googleapis.com/",
        configuration = FeignClientConfig.class)
public interface GoogleFeignClient {

    @GetMapping("/oauth2/v2/userinfo")
    OAuthUserInfoResponse getUserInfo(@RequestParam String access_token);
}
