package com.neodo.neodo_backend.security.config;

import com.neodo.neodo_backend.security.filter.CorsFilter;
import com.neodo.neodo_backend.security.filter.JwtAuthenticationFilter;
import com.neodo.neodo_backend.security.filter.JwtAuthorizationFilter;
import com.neodo.neodo_backend.security.service.LogoutService;
import com.neodo.neodo_backend.security.service.UserDetailsServiceImpl;
import com.neodo.neodo_backend.security.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final LogoutService logoutService;
    private final CorsFilter corsFilter;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtTokenUtils, logoutService);
        filter.setAuthenticationManager(authenticationManager()); // AuthenticationManager 설정
        filter.setFilterProcessesUrl("/api/users/login"); // 커스텀 로그인 URL 설정
        return filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // 기본 설정인 SessionCreationPolicy을 사용하지 않고 JWT 사용하기 위함
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 요청 인증 설정
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.POST, "/api/users/signup").permitAll()  // 회원가입 허용 (POST /api/users)
                        .requestMatchers("/api/users/login").permitAll()  // 로그인 허용
                        .anyRequest().authenticated() // 그 외 요청은 인증 필요
                )

                // 필터 순서 설정
                .addFilterBefore(corsFilter, ChannelProcessingFilter.class) // CORS 필터
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthorizationFilter(jwtTokenUtils, userDetailsServiceImpl, logoutService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
