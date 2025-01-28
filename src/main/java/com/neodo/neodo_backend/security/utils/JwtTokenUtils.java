package com.neodo.neodo_backend.security.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

import static com.neodo.neodo_backend.security.constant.JwtTokenConstant.*;

@Component
@Slf4j(topic = "JwtUtil")
public class JwtTokenUtils {

    private final Key key;

    public JwtTokenUtils(@Value("${jwt.secret.key}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // accessToken 생성
    public String createAccessToken(String email) {
        long now = (new Date()).getTime();

        return BEARER_PREFIX + Jwts.builder()
                .setSubject(email)
                .claim(AUTHORIZATION_KEY, "ROLE_USER")
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // refreshToken 생성
    public String createRefreshToken(String email) {
        long now = (new Date()).getTime();

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 헤더에서 accessToken 가져오기
    public String getAccessToken(HttpServletRequest request) {
        String token = request.getHeader(AUTH_ACCESS_HEADER);
        if (!(StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX))) {
            return null;
        }
        return token.substring(BEARER_PREFIX.length());
    }

    public String getRefreshToken(HttpServletRequest request) {
        // 요청에서 쿠키 배열 가져오기
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            // 특정 쿠키 이름(Refresh Token)에 해당하는 쿠키 검색
            for (Cookie cookie : cookies) {
                if (AUTH_REFRESH_HEADER.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        // 쿠키에서 Refresh Token이 없을 경우 null 반환
        return null;
    }

    // 토큰 검증하기
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key) // 서명 검증에 사용할 키 설정
                    .build()            // 파서 생성
                    .parseClaimsJws(token); // 토큰 파싱 및 검증
            return true; // 검증 성공
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e); // 서명 또는 형식 오류
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e); // 토큰 만료
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e); // 지원되지 않는 토큰
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e); // 토큰이 비어 있음
        }
        return false; // 검증 실패
    }

    // 토큰에서 사용자 정보 가져오기
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // 서명 검증에 사용할 키 설정
                .build()            // 파서 생성
                .parseClaimsJws(token) // JWT 파싱 및 서명 검증
                .getBody();          // Claims 반환
    }

}
