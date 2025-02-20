package com.neodo.neodo_backend.security.service;

import com.neodo.neodo_backend.security.constant.JwtTokenConstant;
import com.neodo.neodo_backend.security.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final JwtTokenUtils jwtTokenUtils;

    // 사용자별 최소 유효 발급 시간 저장 (메모리 기반)
    private final ConcurrentHashMap<String, Long> userLogoutTimestamps = new ConcurrentHashMap<>();

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = request.getHeader(JwtTokenConstant.AUTH_ACCESS_HEADER);
        if (accessToken != null && accessToken.startsWith(JwtTokenConstant.BEARER_PREFIX)) {
            String token = accessToken.substring(JwtTokenConstant.BEARER_PREFIX.length());
            Claims claims = jwtTokenUtils.parseClaims(token);
            String email = claims.getSubject();

            // 현재 시간을 최소 유효 발급 시간으로 설정
            userLogoutTimestamps.put(email, System.currentTimeMillis());

        }

        // Refresh Token 쿠키 만료 처리
        ResponseCookie expiredCookie = ResponseCookie.from(JwtTokenConstant.AUTH_REFRESH_HEADER, "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
        response.addHeader("Set-Cookie", expiredCookie.toString());
    }

    public Long getUserLogoutTimestamp(String email) {
        return userLogoutTimestamps.getOrDefault(email, -1L);
    }

}