package com.neodo.neodo_backend.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.AuthException;
import com.neodo.neodo_backend.security.constant.Role;
import com.neodo.neodo_backend.security.dto.LoginRequest;
import com.neodo.neodo_backend.security.service.LogoutService;
import com.neodo.neodo_backend.security.utils.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.neodo.neodo_backend.security.constant.JwtTokenConstant.AUTH_ACCESS_HEADER;
import static com.neodo.neodo_backend.security.constant.JwtTokenConstant.AUTH_REFRESH_HEADER;

@RequiredArgsConstructor
@Slf4j(topic = "JwtAuthenticationFilter")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final LogoutService logoutService;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) {
        try {
            //json 형태의 String 데이터를 LoginRequest로 변환
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(),
                    LoginRequest.class);

            List<GrantedAuthority> authorities = Collections.singletonList(
                    new SimpleGrantedAuthority(Role.ROLE_USER.name()));

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword(),
                            authorities
                    )
            );
        } catch (IOException e) {
            log.error("로그인 시도(attemptAuthentication) 입출력 예외 발생 {}", e.getMessage());
            throw new AuthException(ErrorResponseEnum.AUTHENTICATION_IO_EXCEPTION);
        }
    }

    // 로그인 성공시 처리
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) {
        // Access Token & Refresh Token 생성
        String email = authentication.getName();
        String accessToken = jwtTokenUtils.createAccessToken(email);
        String refreshToken = jwtTokenUtils.createRefreshToken(email);

        // Access Token을 응답 헤더에 추가
        response.addHeader(AUTH_ACCESS_HEADER, accessToken);

        // Refresh Token을 HttpOnly Secure Cookie로 설정
        ResponseCookie cookie = ResponseCookie.from(AUTH_REFRESH_HEADER, refreshToken)
                .httpOnly(true)
                .secure(false) // 개발환경에서만 false로 설정
                .path("/")
                .sameSite("Lax") // 개발 환경에서는 Lax 설정 (Cross-Site 요청 가능)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }
}
