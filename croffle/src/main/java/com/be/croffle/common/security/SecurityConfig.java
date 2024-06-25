package com.be.croffle.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.util.Arrays;

@Slf4j
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, @Autowired @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) throws Exception {
        //csrf disable
        http.csrf(AbstractHttpConfigurer::disable);

        //cors settings
        http.cors(corsCustomizer -> corsCustomizer.configurationSource(configurationSource()));

        // token method
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        //form 로그인 해제 (UsernamePasswordAuthenticationFilter 비활성화)
        http.formLogin(AbstractHttpConfigurer::disable);

        //username, password 헤더 로그인 방식 해제 (BasicAuthenticationFilter 비활성화)
        http.httpBasic(AbstractHttpConfigurer::disable);

        //커스텀 필터 등록
        //http.apply(new SecurityFilterManagerImpl()); <- deprecated
        // http.with(new SecurityFilterManagerImpl(), securityFilterManager -> {
        //  });

        /*
        // 인증 실패 처리
        http.exceptionHandling(handling ->
                handling.authenticationEntryPoint(((request, response, authException) -> {
                    resolver.resolveException(request, response, null, new UnAuthorizedException(ErrorMessage.UNAUTHORIZED_ERROR));
                })));
         */

        /*
        // 권한 실패 처리
        http.exceptionHandling(handling ->
                handling.accessDeniedHandler(((request, response, accessDeniedException) -> {
                    resolver.resolveException(request, response, null, new ForbiddenException(ErrorMessage.FORBIDDEN_ERROR));
                })));
         */

        //authorization setting
        http.authorizeHttpRequests(authorize ->
                authorize
                        .requestMatchers(new AntPathRequestMatcher("/api/playlist", "GET")).authenticated()
                        .anyRequest().permitAll()
        );

        return http.build();

    }
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8080"));
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
