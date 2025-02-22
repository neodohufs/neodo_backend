package com.neodo.neodo_backend.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(
            ServletRequest req,
            ServletResponse res,
            FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 허용할 Origin(출처)을 설정
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:65252");
        // 브라우저가 인증 정보(쿠키, 인증 헤더 등)를 포함한 요청을 허용할지 설정
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 허용할 HTTP 메서드(GET, POST, PUT 등)를 설정
        response.setHeader("Access-Control-Allow-Methods","*");
        // Preflight 요청의 캐시 지속 시간을 설정
        response.setHeader("Access-Control-Max-Age", "3600");
        // 요청 헤더 중 허용할 헤더 목록을 설정
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Authorization");

        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }

}