package com.be.croffle.common.security;

import com.be.croffle.member.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
public class TokenGenerator {
    private final String PREFIX_TOKEN = "Bearer ";
    private static String SECRET_KEY = "secretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecret";

    public static Key createKey() {
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    //Create access token
    public String createAccessToken(Member member) {
        String token = Jwts.builder().setSubject(member.getGoogleId()) // 정보 저장
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(new Date()) // 토큰 발행 시간
                .setExpiration(calcExpirationDateTime()) // 토큰 만료 시간
                .signWith(createKey(), SignatureAlgorithm.HS256)  // 암호화 알고리즘 및 secretKey
                .compact();

        return PREFIX_TOKEN + token;
    }

    //Set expiration date
    private Date calcExpirationDateTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationDateTime;
        expirationDateTime = currentTime
                .plusDays(7);
        return Date.from(expirationDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());

    }

    public Claims extractToken(String jwt) {
        String token = jwt.substring(7);
        return Jwts.parserBuilder()
                .setSigningKey(createKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}