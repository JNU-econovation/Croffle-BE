package com.be.croffle.common.security;

import com.be.croffle.member.Member;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private static final String HEADER = "Authorization";
    private final TokenGenerator tokenGenerator;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, TokenGenerator tokenGenerator) {
        super(authenticationManager);
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(HEADER);
        //헤더에서 JWT를 받아오고 토큰이 없으면 다음 필터로 넘어감.
        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }
        //헤더에 JWT가 존재한다면 Claims 추출하여 사용자 정보를 이용해 UserDetails 객체를 만들고 이를 이용해 Authentication 객체를 만들어 SecurityContext에 저장
        try {
            Claims claims = tokenGenerator.extractToken(jwt);
            String subjects = claims.getSubject();
            System.out.println("subjects = " + subjects);
            String[] subjectArray = subjects.split(",");

            String googleId = subjectArray[0];
            String name = subjectArray[1];
            //멤버 객체 만듬
            Member member = Member.builder()
                    .name(name)
                    .googleId(googleId)
                    .build();

            //멤버 객체를 이용해 UserDetailsImpl 객체 생성
            UserDetailsImpl userDetails = new UserDetailsImpl(member);

            //인증된 사용자의 정보
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //다음 필터로 넘어감
            filterChain.doFilter(request, response);

        } catch (SecurityException e) {
            throw new JwtException("잘못된 JWT 시그니처");
        } catch (MalformedJwtException e) {
            throw new JwtException("유효하지 않은 JWT 토큰");
        } catch (ExpiredJwtException e) {
            throw new JwtException("토큰 기한 만료");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("지원하지 않는 JWT 토큰");
        } catch (IllegalArgumentException e) {
            throw new JwtException("JWT token compact of handler are invalid.");
        }

    }


}