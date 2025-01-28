package com.neodo.neodo_backend.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neodo.neodo_backend.security.dto.LoginRequest;
import com.neodo.neodo_backend.security.utils.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.neodo.neodo_backend.security.constant.JwtTokenConstant.*;

@RequiredArgsConstructor
@Slf4j(topic = "JwtAuthenticationFilter")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            //json 형태의 String 데이터를 LoginRequest로 변환
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(),
                    LoginRequest.class);

            List<GrantedAuthority> authorities = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_USER"));

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword(),
                            authorities
                    )
            );
        } catch (IOException e) {
            log.error("로그인 시도(attemptAuthentication) 예외 발생 {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    // 로그인 성공시 처리
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException {
        // accessToken, refreshToken 생성
        String email = authentication.getName();
        String accessToken = jwtTokenUtils.createAccessToken(email);
        String refreshToken = jwtTokenUtils.createRefreshToken(email);

        // 헤더에 accessToken 담기
        response.addHeader(AUTH_ACCESS_HEADER, accessToken);

        // HTTP-Only Secure Cookie로 Refresh Token 설정
        ResponseCookie cookie = ResponseCookie.from(AUTH_REFRESH_HEADER, refreshToken)
                .httpOnly(true) // JavaScript에서 접근 불가
                .secure(true) // HTTPS에서만 전송
                .maxAge(REFRESH_TOKEN_EXPIRE_TIME) // 유효기간 (7일)
                .path("/") // 쿠키의 유효 경로
                .sameSite("Strict") // SameSite 설정
                .build();

        // refreshToken을 쿠키로 응답에 추가
        response.addHeader("Set-Cookie", cookie.toString());

        // accessToken JSON 응답에 포함
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        // JSON 형식으로 accessToken 응답
        response.getWriter().write(new ObjectMapper().writeValueAsString(Collections.singletonMap("accessToken", accessToken)));

    }

}
