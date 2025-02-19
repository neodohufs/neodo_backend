package com.neodo.neodo_backend.security.utils;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "TokenBlacklist")
public class TokenBlacklist {

    // ConcurrentHashMap으로 블랙리스트 관리
    private final ConcurrentHashMap<String, Long> blacklist = new ConcurrentHashMap<>();
    private final JwtTokenUtils jwtTokenUtils;

    // 토큰을 블랙리스트에 추가
    public void addTokenToBlacklist(String token, long expirationTime) {
        blacklist.put(token, expirationTime);
        log.info("토큰 블랙리스트에 추가");
    }

    // 토큰이 블랙리스트에 있는지 확인
    public boolean isTokenBlacklisted(String token) {
        Long expirationTime = blacklist.get(token);

        // 만료 시간이 현재 시간보다 이전이면 제거
        if (expirationTime != null && expirationTime < System.currentTimeMillis()) {
            blacklist.remove(token);
            log.info("만료된 토큰 블랙리스트에서 제거");
            return false;
        }
        return expirationTime != null;
    }

    public void clearTokensForUser(String email) {
        blacklist.keySet().removeIf(token -> {
            Claims claims = jwtTokenUtils.parseClaims(token);
            return claims.getSubject().equals(email);
        });
        log.info("해당 유저의 모든 블랙리스트 토큰 제거");
    }
}
