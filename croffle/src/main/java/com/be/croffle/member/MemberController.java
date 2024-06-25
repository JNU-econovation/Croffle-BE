package com.be.croffle.member;

import com.be.croffle.common.ApiResponse;
import com.be.croffle.common.ApiResponseGenerator;
import com.be.croffle.member.oauth.OAuthTokenService;
import com.be.croffle.member.oauth.dto.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final OAuthTokenService oAuthTokenService;

    //redirect uri
    @GetMapping("/login/oauth2/code/google")
    public ApiResponse<ApiResponse.CustomBody<AccessTokenResponse>> oAuthLogin(@RequestParam(value = "code") String code){
        AccessTokenResponse response = oAuthTokenService.getGoogleAccessToken(code);
        return ApiResponseGenerator.success(response, HttpStatus.OK);
    }


}
