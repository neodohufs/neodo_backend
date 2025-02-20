package com.neodo.neodo_backend.security.filter;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.AuthException;
import com.neodo.neodo_backend.security.constant.Role;
import com.neodo.neodo_backend.security.service.LogoutService;
import com.neodo.neodo_backend.security.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

import static com.neodo.neodo_backend.security.constant.JwtTokenConstant.AUTH_ACCESS_HEADER;

@RequiredArgsConstructor
@Slf4j(topic = "JwtAuthorizationFilter")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsService userDetailsService;
    private final LogoutService logoutService;
    //private final TokenBlacklist tokenBlacklist;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        // 인증이 필요 없는 URL 및 HTTP 메서드 설정
        return "/api/users/signup".equals(requestURI) && "POST".equalsIgnoreCase(method);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String accessToken = jwtTokenUtils.getAccessToken(request); // 헤더에서 AccessToken 가져오기

            if (StringUtils.hasText(accessToken)) {

                Claims claims = jwtTokenUtils.parseClaims(accessToken);
                String email = claims.getSubject();
                Date issuedAt = claims.getIssuedAt();

                if (issuedAt == null) {
                    log.warn("발급 시간이 없는 accessToken");
                    throw new AuthException(ErrorResponseEnum.INVALID_TOKEN);
                }

                Long tokenIat = claims.getIssuedAt().getTime();

                // 최소 유효 발급 시간 확인 (로그아웃 시간 이후 발급된 토큰인지 검증)
                Long logoutTimestamp = logoutService.getUserLogoutTimestamp(email);
                if (logoutTimestamp != null && tokenIat < logoutTimestamp) {
                    log.warn("로그아웃된 accessToken");
                    throw new AuthException(ErrorResponseEnum.INVALID_TOKEN);
                }


                if (jwtTokenUtils.validateToken(accessToken)) {
                    log.info("유효한 accessToken");
                    authenticateWithAccessToken(accessToken);
                } else {
                    log.info("유효하지 않은 accessToken");
                    createNewAccessTokenWithRefreshToken(request, response, claims.getSubject());
                }
            }
        } catch (ExpiredJwtException e) {
            log.warn("Access Token has expired: {}", e.getMessage());
            throw new AuthException(ErrorResponseEnum.EXPIRED_TOKEN);
        } catch (Exception e) {
            log.error("Unexpected error during token validation: {}", e.getMessage());
            SecurityContextHolder.clearContext(); // 인증 정보 초기화
            throw new AuthException(ErrorResponseEnum.UNEXPECTED_AUTH_ERROR);
        }

        filterChain.doFilter(request, response);
    }

    // accessToken이 유효 - SecurityContextHolder에 인증 객체 끼우기
    public void authenticateWithAccessToken(String token) {
        Claims claims = jwtTokenUtils.parseClaims(token);
        log.info("Access Token 인증 처리 시작: email={}", claims.getSubject());
        Authentication authentication = createAuthentication(claims.getSubject());
        SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContext에 인증 객체 설정
        log.info("Access Token 인증 처리 완료");
    }

    // accessToken이 유효 - 인증 객체 생성
    private Authentication createAuthentication(String email) {
        log.info("인증 객체 생성 시작");
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        log.info("인증 객체 생성 성공");
        return new UsernamePasswordAuthenticationToken(userDetails, Role.ROLE_USER, userDetails.getAuthorities());
    }

    // accessToken이 유효하지 않은 경우 - 리프레시 토큰 검증 및 엑세스토큰 재발급
    public void createNewAccessTokenWithRefreshToken(HttpServletRequest request,
                                                     HttpServletResponse response, String email) {
        log.info("refreshToken 검증 시도");
        String refreshToken = jwtTokenUtils.getRefreshToken(request);

        if (refreshToken == null || refreshToken.isEmpty()) {
            log.warn("Refresh Token is missing or empty. Clearing security context.");
            SecurityContextHolder.clearContext();
            throw new AuthException(ErrorResponseEnum.TOKEN_NOT_FOUND);
        }
        if (jwtTokenUtils.validateToken(refreshToken)) {
            log.info("유효한 refreshToken");
            String newAccessToken = jwtTokenUtils.createAccessToken(email); // accessToken 다시 생성, 헤더에 전달
            response.addHeader(AUTH_ACCESS_HEADER, newAccessToken);
            authenticateWithAccessToken(newAccessToken);
        } else {
            log.warn("유효하지 않은 Refresh Token: email={}", email);
            SecurityContextHolder.clearContext(); // 인증 정보 초기화
            throw new AuthException(ErrorResponseEnum.INVALID_TOKEN);
        }
    }
}
